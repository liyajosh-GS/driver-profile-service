package com.ridemanagement.driverservice.kafka.producer;

import org.springframework.stereotype.Component;

@Component
public interface KafkaProducer {

    void sendMessage(String message);
}
