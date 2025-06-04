package com.ms.news.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;

import java.util.Objects;

@Configuration
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private String port;

    @Value("${spring.data.redis.password}")
    private String password;

    @Bean
    public ReactiveRedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(Objects.requireNonNull(host));
        redisStandaloneConfiguration.setPort(Integer.parseInt(port));
        redisStandaloneConfiguration.setPassword(Objects.requireNonNull(password));
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public ReactiveRedisOperations<String, Object> reactiveRedisOperationsExtensionsKt() {

    }
}
