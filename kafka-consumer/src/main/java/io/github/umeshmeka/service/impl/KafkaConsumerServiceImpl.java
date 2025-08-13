package io.github.umeshmeka.service.impl;

import io.github.umeshmeka.model.User;
import io.github.umeshmeka.service.KafkaConsumerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    // Below aproach is used to read messages from Kafka topics.
    // The @KafkaListener annotation is used to mark a method as a listener for a specific Kafka topic.
    // But this works only with one Kafka deserializer at a time. (string or json
//    @KafkaListener(topics = "${kafka.string.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
//    public void readStringOrEventMessage(String message) {
//        System.out.println("Received message: " + message);
//    }
//
//    @KafkaListener(topics = "${kafka.json.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
//    public void readJSONMessage(String message) {
//        System.out.println("Received JSON message: " + message);
//    }

    // To work with different deserializers, we can use the below approach.
    @KafkaListener(topics = "${kafka.string.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "stringKafkaListenerContainerFactory")
    public void readStringOrEventMessage(String message) {
        System.out.println("Received message: " + message);
    }

    @KafkaListener(topics = "${kafka.json.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "jsonKafkaListenerContainerFactory")
    public void readJSONMessage(User user) {
        System.out.println("Received JSON message: " + user.toString());
    }

}
