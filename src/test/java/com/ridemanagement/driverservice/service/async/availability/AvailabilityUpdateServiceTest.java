package com.ridemanagement.driverservice.service.async.availability;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridemanagement.driverservice.DtoEntityHelperClass;
import com.ridemanagement.driverservice.entity.DriverKey;
import com.ridemanagement.driverservice.kafka.producer.AvailabilityProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AvailabilityUpdateServiceTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private AvailabilityProducer producer;

    @InjectMocks
    private AvailabilityUpdateService availabilityUpdateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendAvailabilityUpdate() throws JsonProcessingException {
        DriverKey driverKey = DtoEntityHelperClass.getDriverKey();
        boolean isAvailable = true;
        when(objectMapper.writeValueAsString(driverKey)).thenReturn("mockKeyString");
        doNothing().when(producer).sendMessage("message");
        CompletableFuture<Void> result = availabilityUpdateService.sendAvailabilityUpdate(driverKey, isAvailable);

        assertNotNull(result);

        result.join();
    }

    @Test
    void testSendAvailabilityWithException() throws JsonProcessingException {
        DriverKey driverKey = DtoEntityHelperClass.getDriverKey();
        boolean isAvailable = true;
        when(objectMapper.writeValueAsString(driverKey)).thenThrow(new RuntimeException("Error processing JSON: "));
        assertThrows(RuntimeException.class, () -> {
            availabilityUpdateService.sendAvailabilityUpdate(driverKey, isAvailable).join();
        });
        verify(producer, never()).sendMessage(anyString());
    }

    private static class MockJsonProcessingException extends JsonProcessingException {
        public MockJsonProcessingException(String message) {
            super(message);
        }
    }

    @Test
    void testSendAvailabilityWithJsonProcessingException() throws JsonProcessingException {
        var exception = new MockJsonProcessingException("Error processing JSON: ");
        DriverKey driverKey = DtoEntityHelperClass.getDriverKey();
        boolean isAvailable = true;
        when(objectMapper.writeValueAsString(driverKey)).thenThrow(exception);
        assertThrows(RuntimeException.class, () -> {
            availabilityUpdateService.sendAvailabilityUpdate(driverKey, isAvailable).join();
        });
        verify(producer, never()).sendMessage(anyString());
    }
}
