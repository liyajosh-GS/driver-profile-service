package com.ridemanagement.driverservice.service.async.availability;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridemanagement.driverservice.entity.DriverKey;
import com.ridemanagement.driverservice.kafka.producer.AvailabilityProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AvailabilityUpdateService {

    @Autowired
    AvailabilityProducer producer;

    @Autowired
    ObjectMapper objectMapper;

    public CompletableFuture<Void> sendAvailabilityUpdate(DriverKey driverKey, boolean isAvailabile) {
        return CompletableFuture.runAsync(() -> {
            try {

                    String key = objectMapper.writeValueAsString(driverKey);
                    String value = String.format("{\"isAvailable\":\"%s\"}", isAvailabile);
                    producer.sendMessage(key + "-" + value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Error processing JSON: " + e.getMessage());
                    // Handle the exception or log it as needed
                } catch (RuntimeException e) {
                    throw new RuntimeException("Run time: " + e.getMessage());
            }

        });
    }
}



