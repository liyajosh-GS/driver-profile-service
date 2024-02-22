package com.ridemanagement.driverservice.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ridemanagement.driverservice.entity.DocumentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
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
