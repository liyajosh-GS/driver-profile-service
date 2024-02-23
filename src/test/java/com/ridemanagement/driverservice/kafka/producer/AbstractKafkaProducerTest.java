package com.ridemanagement.driverservice.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;

public class AbstractKafkaProducerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private AbstractKafkaProducerService documentVerificationProducer =  new DocumentVerificationProducer();
    @InjectMocks
    private AbstractKafkaProducerService availabilityProducer =  new AvailabilityProducer();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStartVerification() throws JsonProcessingException {
        when(kafkaTemplate.send(any(),anyString())).thenReturn(CompletableFuture.completedFuture(any()));
        documentVerificationProducer.sendMessage(anyString());

        verify(kafkaTemplate, times(1)).send(any(),anyString());

    }

    @Test
    void testSendAvailabilityUpdate() throws JsonProcessingException {
        when(kafkaTemplate.send(any(),anyString())).thenReturn(CompletableFuture.completedFuture(any()));
        availabilityProducer.sendMessage(anyString());

        verify(kafkaTemplate, times(1)).send(any(),anyString());

    }

}
