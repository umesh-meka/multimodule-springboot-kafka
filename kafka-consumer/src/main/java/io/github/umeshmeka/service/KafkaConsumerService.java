package io.github.umeshmeka.service;

import io.github.umeshmeka.model.User;

public interface KafkaConsumerService {
    void readStringOrEventMessage(String message);

    void readJSONMessage(User user);
}
