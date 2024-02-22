package com.ridemanagement.driverservice.service.async.verification;

import com.ridemanagement.driverservice.dto.AbstractDto;
import com.ridemanagement.driverservice.entity.AbstractKey;
import org.springframework.beans.factory.annotation.Value;


public abstract class AbstractPersonalVerificationService<K extends AbstractKey, D extends AbstractDto> extends AbstractDocumentVerificationService<K, D> implements PersonalVerificationService<K, D> {

    @Value("${kafka.topics.document-verification-scheduler}")
    private String topic;

    protected abstract String getValue();
//
//    @Override
//    protected String getTopic() {
//        return topic;
//    }
}
