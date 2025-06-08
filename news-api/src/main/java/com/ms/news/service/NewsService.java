package com.ms.news.service;

import reactor.core.publisher.Mono;

public interface NewsService {
  Mono<Void> publishMessage(String date);

  Mono<Object> getNews(String date);
}
