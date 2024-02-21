package com.ridemanagement.driverservice.service.async.verificationService;

import com.ridemanagement.driverservice.dto.Document;
import com.ridemanagement.driverservice.entity.DocumentEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public interface DocumentVerificationService {

    CompletableFuture<Void> startVerification(DocumentEntity document);

    CompletableFuture<Void> updateVerificationStatus(Document document);
}
