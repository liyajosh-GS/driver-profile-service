package com.ridemanagement.driverservice.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractKafkaProducer implements KafkaProducer{

    private final KafkaTemplate<String, String> kafkaTemplate;

    protected abstract String getTopic();

    protected abstract String getMessage();

    @Autowired
    public AbstractKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(getTopic(), getMessage());
        System.out.println("Message sent to Kafka: " + message);
    }

}
