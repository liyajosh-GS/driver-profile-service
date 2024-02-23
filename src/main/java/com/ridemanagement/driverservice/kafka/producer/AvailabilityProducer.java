package com.ridemanagement.driverservice.kafka.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityProducer extends AbstractKafkaProducerService {

    @Value("${kafka.topics.driver-availability}")
    private String topic;

    @Override
    protected String getTopic() {
        return topic;
    }
}
