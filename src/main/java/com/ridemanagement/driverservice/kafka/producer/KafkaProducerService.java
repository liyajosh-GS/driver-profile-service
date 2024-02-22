package com.ridemanagement.driverservice.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ridemanagement.driverservice.entity.AbstractEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface KafkaProducerService {

    void sendMessage(String message);
}
