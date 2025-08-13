package io.github.umeshmeka;

import io.github.umeshmeka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class KafkaProducer implements CommandLineRunner {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(KafkaProducer.class, args);
    }

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Override
    public void run(String... args) throws InterruptedException {
        kafkaProducerService.sendMessage("Hello, Umesh Meka from Kafka Producer! -- " + LocalDateTime.now() + "");
        kafkaProducerService.sendJSONMessage();
        kafkaProducerService.sendEventMessage();
    }
}
