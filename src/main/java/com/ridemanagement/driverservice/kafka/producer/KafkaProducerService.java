package com.ridemanagement.driverservice.kafka.producer;

import org.springframework.stereotype.Service;

@Service
public interface KafkaProducerService {

    void sendMessage(String message);
}
