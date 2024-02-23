package com.ridemanagement.driverservice.service.async.verification;

import com.ridemanagement.driverservice.entity.DocumentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VerificationServiceFactoryTest {

    @Mock
    private IdVerificationService idVerificationService;

    @Mock
    private LicenseVerificationService licenseVerificationService;

    @Mock
    private VehicleRegistrationVerificationService vehicleRegistrationVerificationService;

    @InjectMocks
    private VerificationServiceFactory verificationServiceFactory;

    @BeforeEach
    void setUp() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        MockitoAnnotations.openMocks(this);
        Method initializeVerificationServicesMethod = VerificationServiceFactory.class.getDeclaredMethod("initializeVerificationServices");
        initializeVerificationServicesMethod.setAccessible(true);
        initializeVerificationServicesMethod.invoke(verificationServiceFactory);
    }

    @Test
    void testGetServiceForIdVerification() {
        DocumentVerificationService result = verificationServiceFactory.getService(DocumentType.ID);

        assertEquals(idVerificationService, result);
    }

    @Test
    void testGetServiceForLicenseVerification() {
        DocumentVerificationService result = verificationServiceFactory.getService(DocumentType.LICENSE);

        assertEquals(licenseVerificationService, result);
    }

    @Test
    void testGetServiceForVehicleRegistrationVerification() {
        DocumentVerificationService result = verificationServiceFactory.getService(DocumentType.VEHICLE_REGISTRATION);

        assertEquals(vehicleRegistrationVerificationService, result);
    }

    @Test
    void testGetServiceForUnknownDocumentType() {
        DocumentVerificationService result = verificationServiceFactory.getService(DocumentType.UNKNOWN);

        // Ensure that null is returned for an unknown document type
        assertEquals(null, result);
    }
}

