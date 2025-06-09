package com.ms.worker_service.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.worker_service.repository.NewsRepository;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class NewsRepositoryImpl implements NewsRepository {
  private final ReactiveRedisOperations<String, Object> redisOperations;

  @Override
  public Mono<Boolean> saveNews(String date, Object data) throws JsonProcessingException {
    Duration ttl = Duration.ofHours(1);
    ObjectMapper objectMapper = new ObjectMapper();
    return redisOperations.opsForValue().set(date, objectMapper.readTree(data.toString()), ttl);
  }
}
