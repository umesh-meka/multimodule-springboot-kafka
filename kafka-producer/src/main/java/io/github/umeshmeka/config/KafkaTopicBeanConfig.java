package io.github.umeshmeka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicBeanConfig {

    @Value("${kafka.string.topic.name}")
    private String stringTopic;

    @Value("${kafka.json.topic.name}")
    private String jsonTopic;

     @Bean
     public NewTopic myTopic() {
         return TopicBuilder.name(stringTopic)
                 .build();
     }

    @Bean
    public NewTopic myJSONTopic() {
        return TopicBuilder.name(jsonTopic)
                .build();
    }
}
