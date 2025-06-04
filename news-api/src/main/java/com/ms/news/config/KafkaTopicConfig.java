package com.ms.news.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic newsTopic() {
        // se define el topic, osea el canal por el cual se publicarán mensajes
        return TopicBuilder.name("news").build(); // TODO: "news" podría ser una constante
    }
}
