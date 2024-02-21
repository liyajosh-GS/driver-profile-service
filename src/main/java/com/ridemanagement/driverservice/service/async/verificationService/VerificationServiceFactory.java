package com.ridemanagement.driverservice.service.async.verificationService;

import com.ridemanagement.driverservice.entity.DocumentType;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class VerificationServiceFactory {

    private Map<DocumentType, DocumentVerificationService> verificationServices;

    @PostConstruct
    private void initializeVerificationServices() {
        this.verificationServices = new EnumMap<>(DocumentType.class);
        this.verificationServices.put(DocumentType.ID, new IdVerificationService());
        this.verificationServices.put(DocumentType.LICENSE, new LicenseVerificationService());
        this.verificationServices.put(DocumentType.VEHICLE_REGISTRATION, new VehicleRegistrationService());
    }

    public DocumentVerificationService getService(DocumentType documentType) {
        return verificationServices.get(documentType);
    }
}
