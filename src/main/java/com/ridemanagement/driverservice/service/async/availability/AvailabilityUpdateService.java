package com.ridemanagement.driverservice.service.async.availability;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridemanagement.driverservice.entity.DocumentType;
import com.ridemanagement.driverservice.entity.DriverKey;
import com.ridemanagement.driverservice.kafka.producer.AvailabilityProducer;
import com.ridemanagement.driverservice.kafka.producer.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AvailabilityUpdateService {

    @Autowired
    AvailabilityProducer producer;

    public CompletableFuture<Void> sendAvailabilityUpdate(DriverKey driverKey, boolean isAvailabile) {
        return CompletableFuture.runAsync(() -> {
            try {

                    ObjectMapper objectMapper = new ObjectMapper();
                    String key = objectMapper.writeValueAsString(driverKey);
                    System.out.println("key " + key);
                    String value = String.format("{\"isAvailable\":\"%s\"}", isAvailabile);
                    System.out.println("value " + value);
                    producer.sendMessage(key + "-" + value);
                } catch (JsonProcessingException e) {
                    System.err.println("Error processing JSON: " + e.getMessage());
                    // Handle the exception or log it as needed
                } catch (RuntimeException e) {
                    System.err.println("Run time: " + e.getMessage());
            }

        });
    }
}



