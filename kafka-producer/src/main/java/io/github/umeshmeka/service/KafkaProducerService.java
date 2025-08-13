package io.github.umeshmeka.service;

public interface KafkaProducerService {
    void sendMessage(String message);
    void sendEventMessage() throws InterruptedException;
    void sendJSONMessage();
}
