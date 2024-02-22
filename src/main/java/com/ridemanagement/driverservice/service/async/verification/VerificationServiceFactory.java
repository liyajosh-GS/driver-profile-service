package com.ridemanagement.driverservice.service.async.verification;

import com.ridemanagement.driverservice.dto.AbstractDto;
import com.ridemanagement.driverservice.entity.AbstractKey;
import com.ridemanagement.driverservice.entity.DocumentType;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class VerificationServiceFactory {

    private Map<DocumentType, DocumentVerificationService<? extends AbstractKey, ? extends AbstractDto>> verificationServices;

    @Autowired
    private IdVerificationService idVerificationService;

    @PostConstruct
    private void initializeVerificationServices() {
        this.verificationServices = new EnumMap<>(DocumentType.class);
        this.verificationServices.put(DocumentType.ID, idVerificationService);
//        this.verificationServices.put(DocumentType.LICENSE, new LicenseVerificationService());
//        this.verificationServices.put(DocumentType.VEHICLE_REGISTRATION, new VehicleRegistrationService());
    }

    public DocumentVerificationService getService(DocumentType documentType) {
        return verificationServices.get(documentType);
    }
}
