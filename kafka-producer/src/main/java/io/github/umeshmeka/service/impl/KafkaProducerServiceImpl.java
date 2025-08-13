package io.github.umeshmeka.service.impl;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import io.github.umeshmeka.config.ChangesHandler;
import io.github.umeshmeka.model.User;
import io.github.umeshmeka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

     // These are default imports for KafkaTemplate
     // But if we want two different types of messages, we can use two different KafkaTemplate
     // That we have configured in KafkaProducerConfig.Java
     //@Autowired
     //private KafkaTemplate<String, String> kafkaTemplate;
     //private KafkaTemplate<String, User> kafkaTemplate;

     @Autowired
     @Qualifier("stringKafkaTemplate")
     private KafkaTemplate<String, String> stringKafkaTemplate;

     @Autowired
     @Qualifier("jsonKafkaTemplate")
     private KafkaTemplate<String, Object> jsonKafkaTemplate;

     @Value("${kafka.string.topic.name}")
     private String stringTopic;

     @Value("${kafka.json.topic.name}")
     private String jsonTopic;

     public void sendMessage(String message) {
          stringKafkaTemplate.send(stringTopic, message);
     }

     public void sendJSONMessage() {
          Message<User> userMessage = MessageBuilder
                  .withPayload(new User("umesh", ""+ LocalDateTime.now()))
                  .setHeader(KafkaHeaders.TOPIC, jsonTopic)
                  .build();
          jsonKafkaTemplate.send(userMessage);
     }

     public void sendEventMessage() throws InterruptedException {
          BackgroundEventHandler eventHandler = new ChangesHandler(stringKafkaTemplate, stringTopic);
          URI uri = URI.create("https://stream.wikimedia.org/v2/stream/recentchange");

          EventSource.Builder eventSourceBuilder = new EventSource.Builder(uri);
          BackgroundEventSource eventSource = new BackgroundEventSource.Builder(eventHandler, eventSourceBuilder).build();
          eventSource.start();

          TimeUnit.SECONDS.sleep(2);
          eventSource.close();
     }
}
