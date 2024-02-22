package com.ridemanagement.driverservice.service.async.verification;

import com.ridemanagement.driverservice.dto.Document;
import com.ridemanagement.driverservice.entity.DocumentKey;
import com.ridemanagement.driverservice.entity.DocumentType;
import org.springframework.stereotype.Component;

@Component
public class IdVerificationService extends AbstractPersonalVerificationService<DocumentKey, Document> implements PersonalVerificationService <DocumentKey, Document> {


    @Override
    protected String getValue() {
        return String.format("{\"documentType\":\"%s\"}", DocumentType.ID);
    }
}
