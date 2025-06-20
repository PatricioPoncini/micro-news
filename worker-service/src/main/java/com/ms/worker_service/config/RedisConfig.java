package com.ms.worker_service.config;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
  @Value("${spring.data.redis.host}")
  private String host;

  @Value("${spring.data.redis.port}")
  private String port;

  @Value("${spring.data.redis.password}")
  private String password;

  @Bean
  public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setHostName(Objects.requireNonNull(host));
    redisStandaloneConfiguration.setPort(Integer.parseInt(port));
    redisStandaloneConfiguration.setPassword(Objects.requireNonNull(password));
    return new LettuceConnectionFactory(
        redisStandaloneConfiguration); // instancia para redis reactivo
  }

  @Bean
  public ReactiveRedisOperations<String, Object> redisOperations(
      ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
    // se indica que la key será un string y el value será un JSON
    Jackson2JsonRedisSerializer<Object> serializer =
        new Jackson2JsonRedisSerializer<>(Object.class);
    RedisSerializationContext.RedisSerializationContextBuilder<String, Object> builder =
        RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
    RedisSerializationContext<String, Object> context =
        builder.value(serializer).hashKey(serializer).hashValue(serializer).build();
    return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, context);
  }
}
