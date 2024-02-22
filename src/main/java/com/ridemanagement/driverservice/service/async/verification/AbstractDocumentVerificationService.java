package com.ridemanagement.driverservice.service.async.verification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridemanagement.driverservice.dto.AbstractDto;
import com.ridemanagement.driverservice.entity.AbstractKey;
import com.ridemanagement.driverservice.kafka.producer.DocumentVerificationProducer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;

public abstract class AbstractDocumentVerificationService<K extends AbstractKey, D extends AbstractDto> implements DocumentVerificationService<K, D>{

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DocumentVerificationProducer producer;

    protected abstract String getValue();


    private String processMessage(K k){
        try {
            System.out.println("Sending Kafka message to scheduler to start ID verification");
            ObjectMapper objectMapper = new ObjectMapper();
            String key = objectMapper.writeValueAsString(k);
            System.out.println("key " + key);
            String value = getValue();
            System.out.println("value " + value);

            return key + "-" + value;
        } catch (JsonProcessingException e) {
            System.err.println("Error processing JSON: " + e.getMessage());
            // Handle the exception or log it as needed
        }

        return "";
    }


    @Override
    public CompletableFuture<Void> startVerification(K k) throws JsonProcessingException {
        return CompletableFuture.runAsync(() -> {
            try {
                System.out.println("Sending Kafka message to scheduler to start ID verification");
                ObjectMapper objectMapper = new ObjectMapper();
                String key = objectMapper.writeValueAsString(k);
                System.out.println("key " + key);
                String value = getValue();
                System.out.println("value " + value);
                producer.sendMessage(key + "-" + value);
            } catch (JsonProcessingException e) {
                System.err.println("Error processing JSON: " + e.getMessage());
                // Handle the exception or log it as needed
            } catch (RuntimeException e){
                System.err.println("Run time: " + e.getMessage());
            }

        });
       // kafkaMessenger.sendMessage(kafkaTemplate, getTopic(), processMessage(k));

    }

    @Override
    public void updateVerificationStatus(D d) {
        return ;
    }
}
