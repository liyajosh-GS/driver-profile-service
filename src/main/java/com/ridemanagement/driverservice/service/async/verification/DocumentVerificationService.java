package com.ridemanagement.driverservice.service.async.verification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ridemanagement.driverservice.dto.AbstractDto;
import com.ridemanagement.driverservice.entity.AbstractKey;

import java.util.concurrent.CompletableFuture;


public interface DocumentVerificationService<K extends AbstractKey, D extends AbstractDto> {

    CompletableFuture<Void> startVerification(K k) throws JsonProcessingException;

    void updateVerificationStatus(D d);
}
