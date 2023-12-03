package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;


@Configuration

public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic() {
        Map<String, String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);//borrar mensajes viejos
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");//retener mensajes por 1 dia
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");// puede soportar un maximo de 1GB
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1048576");//cada mensaje puede ser maximo de 1MB

        return TopicBuilder.name("test-topic")
                .partitions(2)
                .replicas(2)
                .configs(configurations)
                .build();
    }

}
