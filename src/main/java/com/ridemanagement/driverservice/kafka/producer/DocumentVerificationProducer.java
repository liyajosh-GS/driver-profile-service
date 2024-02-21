package com.ridemanagement.driverservice.kafka.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DocumentVerificationProducer extends AbstractKafkaProducer{

    @Value("${kafka.topics.document-verification-scheduler}")
    private String topic;

    @Override
    protected String getTopic() {
        return topic;
    }

    @Override
    protected String getMessage() {
        return null;
    }

    public void
}
