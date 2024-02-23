package com.ridemanagement.driverservice.kafka.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DocumentVerificationProducer extends AbstractKafkaProducerService{

    @Value("${kafka.topics.document-verification-scheduler}")
    private String topic;

    @Override
    protected String getTopic() {
        return topic;
    }

}
