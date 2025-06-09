package com.ms.worker_service.listener;

import static com.ms.worker_service.utils.Constants.MESSAGE_GROUP_NAME;
import static com.ms.worker_service.utils.Constants.TOPIC_NAME;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ms.worker_service.model.exceptions.ExternalApiException;
import com.ms.worker_service.repository.NewsRepository;
import com.ms.worker_service.service.MediastackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaListeners {
  // al consumir el evento, se hace la llamada a la API externa y se guarda en Redis la data
  private final MediastackService mediastackService;
  private final NewsRepository newsRepository;

  @KafkaListener(topics = TOPIC_NAME, groupId = MESSAGE_GROUP_NAME)
  public void listener(String date) {
    log.info("Listener received: {}", date);
    mediastackService
        .sendRequest(date)
        .flatMap(
            response -> {
              try {
                return newsRepository.saveNews(date, response.getBody());
              } catch (JsonProcessingException e) {
                return Mono.error(new RuntimeException("Error processing JSON", e));
              }
            })
        // con doOnNext se busca ejecutar algo cuando se retorna un true
        .doOnNext(
            saved -> {
              if (saved) {
                log.info("News saved");
              } else {
                log.error("News not saved");
              }
            })
        // con doOnError se maneja cualquier error que pueda ocurrir
        .doOnError(
            error -> {
              if (error instanceof ExternalApiException apiException) {
                log.error(
                    "Failure in external API. Status: {}. Body: {}",
                    apiException.getStatus(),
                    apiException.getMessage());
              } else {
                log.error("Unexpected error: {}", error.getMessage(), error);
              }
            })
        .subscribe();
  }
}
