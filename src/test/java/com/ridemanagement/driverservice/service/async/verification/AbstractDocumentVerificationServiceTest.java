package com.ridemanagement.driverservice.service.async.verification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridemanagement.driverservice.DtoEntityHelperClass;
import com.ridemanagement.driverservice.dto.AbstractDto;
import com.ridemanagement.driverservice.entity.AbstractKey;
import com.ridemanagement.driverservice.entity.DriverKey;
import com.ridemanagement.driverservice.kafka.producer.DocumentVerificationProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AbstractDocumentVerificationServiceTest<K extends AbstractKey, D extends AbstractDto> {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private DocumentVerificationProducer producer;

    @InjectMocks
    private AbstractDocumentVerificationService<K, D> documentVerificationService = (AbstractDocumentVerificationService<K, D>) new IdVerificationService();

    @InjectMocks
    private AbstractDocumentVerificationService<K, D> licenseVerificationService = (AbstractDocumentVerificationService<K, D>) new LicenseVerificationService();

    @InjectMocks
    private AbstractDocumentVerificationService<K, D> vehicleRegistration = (AbstractDocumentVerificationService<K, D>) new VehicleRegistrationVerificationService();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStartVerification() throws JsonProcessingException {
        DriverKey mockKey = DtoEntityHelperClass.getDriverKey();
        when(objectMapper.writeValueAsString(mockKey)).thenReturn("mockKeyString");
        doNothing().when(producer).sendMessage("message");
        CompletableFuture<Void> result = documentVerificationService.startVerification((K) mockKey);
        result.join();
        assertNotNull(result);
    }

    @Test
    void testStartVerificationWithException() throws JsonProcessingException {
        DriverKey mockKey = mock(DriverKey.class);
        when(objectMapper.writeValueAsString(mockKey)).thenThrow(new RuntimeException("Error processing JSON: "));
        assertThrows(RuntimeException.class, () -> {
            documentVerificationService.startVerification((K) mockKey).join();
        });
        verify(producer, never()).sendMessage(anyString());
    }

    private static class MockJsonProcessingException extends JsonProcessingException {
        public MockJsonProcessingException(String message) {
            super(message);
        }
    }

    @Test
    void testStartVerificationWithJsonProcessingException() throws JsonProcessingException {
        var exception = new MockJsonProcessingException("Error processing JSON: ");
        DriverKey mockKey = mock(DriverKey.class);
        when(objectMapper.writeValueAsString(mockKey)).thenThrow(exception);
        assertThrows(RuntimeException.class, () -> {
            documentVerificationService.startVerification((K) mockKey).join();
        });
        verify(producer, never()).sendMessage(anyString());
    }

    @Test
    void testLicenseStartVerification() throws JsonProcessingException {
        DriverKey mockKey = DtoEntityHelperClass.getDriverKey();
        when(objectMapper.writeValueAsString(mockKey)).thenReturn("mockKeyString");
        doNothing().when(producer).sendMessage("message");
        CompletableFuture<Void> result = licenseVerificationService.startVerification((K) mockKey);
        result.join();
        assertNotNull(result);
    }

    @Test
    void testVehicleRegistrationStartVerification() throws JsonProcessingException {
        DriverKey mockKey = DtoEntityHelperClass.getDriverKey();
        when(objectMapper.writeValueAsString(mockKey)).thenReturn("mockKeyString");
        doNothing().when(producer).sendMessage("message");
        CompletableFuture<Void> result = vehicleRegistration.startVerification((K) mockKey);
        result.join();
        assertNotNull(result);
    }

}
