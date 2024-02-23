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


    @Override
    public CompletableFuture<Void> startVerification(K k)  {
        return CompletableFuture.runAsync(() -> {
            try {
                String key = objectMapper.writeValueAsString(k);
                String value = getValue();
                producer.sendMessage(key + "-" + value);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error processing JSON: " + e.getMessage());
                // Handle the exception or log it as needed
            } catch (RuntimeException e){
                throw new RuntimeException("Run time: " + e.getMessage());
            }

        });
    }

    @Override
    public void updateVerificationStatus(D d) {
        return ;
    }
}
