package com.ridemanagement.driverservice.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridemanagement.driverservice.entity.AbstractEntity;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Component
@Slf4j
public abstract class AbstractKafkaProducerService implements KafkaProducerService{

    @Autowired
    @Qualifier("kafkaTemplate")
    private KafkaTemplate<String, String> kafkaTemplate;

    protected abstract String getTopic();

//    @Override
//    public void sendMessage(String message) {
//        kafkaTemplate.send(getTopic(), message);
//        System.out.println("Message sent to Kafka: " + message);
//    }

    public void sendMessage(String message) {
        kafkaTemplate.send(getTopic(), message);
    }
}
