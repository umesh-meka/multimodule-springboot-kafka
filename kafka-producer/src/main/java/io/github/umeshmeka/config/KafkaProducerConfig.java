package io.github.umeshmeka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

        // ---------- Kafka server ----------
        @Value("${spring.kafka.producer.bootstrap-servers}")
        private String bootstrapServers;


        // ---------- STRING Serializer ----------
        @Value("${spring.kafka.producer.string.key-serializer}")
        String stringKeySerializer;
        @Value("${spring.kafka.producer.string.value-serializer}")
        String stringValueSerializer;


        // ---------- JSON Serializer ----------
        @Value("${spring.kafka.producer.json.key-serializer}")
        String jsonKeySerializer;
        @Value("${spring.kafka.producer.json.value-serializer}")
        String jsonValueSerializer;


        // ---------- STRING Producer ----------
        @Bean
        public ProducerFactory<String, String> stringProducerFactory() {
            Map<String, Object> props = new HashMap<>();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, stringKeySerializer);
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, stringValueSerializer);
            return new DefaultKafkaProducerFactory<>(props);
        }

        @Bean
        public KafkaTemplate<String, String> stringKafkaTemplate() {
            return new KafkaTemplate<>(stringProducerFactory());
        }


        // ---------- JSON Producer ----------
        @Bean
        public ProducerFactory<String, Object> jsonProducerFactory() {
            Map<String, Object> props = new HashMap<>();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, jsonKeySerializer);
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, jsonValueSerializer);
            return new DefaultKafkaProducerFactory<>(props);
        }

        @Bean
        public KafkaTemplate<String, Object> jsonKafkaTemplate() {
            return new KafkaTemplate<>(jsonProducerFactory());
        }
}
