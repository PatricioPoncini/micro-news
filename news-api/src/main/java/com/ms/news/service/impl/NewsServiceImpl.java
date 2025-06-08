package com.ms.news.service.impl;

import com.ms.news.repository.NewsRepository;
import com.ms.news.service.NewsService;
import com.ms.news.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final NewsRepository newsRepository;

  @Override
  public Mono<Void> publishMessage(String date) {
    ProducerRecord<String, String> record = new ProducerRecord<>(Constants.TOPIC_NEWS, null, date);
    return Mono.fromFuture(kafkaTemplate.send(record)).then();
  }

  @Override
  public Mono<Object> getNews(String date) {
    return newsRepository
        .getNews(date)
        .flatMap(Mono::just)
        .switchIfEmpty(Mono.defer(() -> publishMessage(date)));
  }
}
