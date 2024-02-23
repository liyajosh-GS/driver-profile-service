package com.ridemanagement.driverservice.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public abstract class AbstractKafkaProducerService implements KafkaProducerService{

    @Autowired
    @Qualifier("kafkaTemplate")
    private KafkaTemplate<String, String> kafkaTemplate;

    protected abstract String getTopic();


    public void sendMessage(String message) {
        kafkaTemplate.send(getTopic(), message);
    }
}
