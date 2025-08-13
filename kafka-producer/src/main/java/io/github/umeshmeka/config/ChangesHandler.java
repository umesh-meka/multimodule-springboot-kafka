package io.github.umeshmeka.config;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
public class ChangesHandler implements BackgroundEventHandler {
    private static final org.slf4j.Logger log
            = org.slf4j.LoggerFactory.getLogger(ChangesHandler.class);
    private KafkaTemplate<String, String> kafkaTemplate;
    private String topic;

    public ChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void onOpen() {
        // Handle the opening of the event stream
    }

    @Override
    public void onClosed() {
        // Handle the closing of the event stream
    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        log.info("Sending message to Kafka topic {}: {}", topic, messageEvent.getData());
        kafkaTemplate.send(topic, messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable t) {
        // Handle errors that occur during event processing
    }
}
