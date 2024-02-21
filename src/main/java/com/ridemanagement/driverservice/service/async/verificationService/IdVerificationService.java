package com.ridemanagement.driverservice.service.async.verificationService;

import com.ridemanagement.driverservice.dto.Document;
import com.ridemanagement.driverservice.entity.DocumentEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class IdVerificationService implements PersonalVerificationService{
    @Override
    public CompletableFuture<Void> startVerification(DocumentEntity document) {
        System.out.println("send kafka message to scheduler to start ID verification");
        return null;
    }

    @Override
    public CompletableFuture<Void> updateVerificationStatus(Document document) {
        return null;
    }
}
