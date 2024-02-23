package com.ridemanagement.driverservice.service.impl;

import com.ridemanagement.driverservice.DtoEntityHelperClass;
import com.ridemanagement.driverservice.dto.Document;
import com.ridemanagement.driverservice.dto.Driver;
import com.ridemanagement.driverservice.entity.DocumentEntity;
import com.ridemanagement.driverservice.entity.DocumentType;
import com.ridemanagement.driverservice.entity.DriverEntity;
import com.ridemanagement.driverservice.mapper.DocumentDtoEntityMapper;
import com.ridemanagement.driverservice.mapper.DriverDtoEntityMapper;
import com.ridemanagement.driverservice.repository.DocumentRepository;
import com.ridemanagement.driverservice.repository.DriverRepository;
import com.ridemanagement.driverservice.service.async.availability.AvailabilityUpdateService;
import com.ridemanagement.driverservice.service.async.verification.DocumentVerificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class DriverServiceTest {

    @Mock
    DriverRepository driverRepository;

    @Mock
    DriverDtoEntityMapper driverDtoEntityMapper;

    @Mock
    AvailabilityUpdateService availabilityUpdateService;

    @InjectMocks
    private DriverService driverService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAvailabilityUpdate() {
        Driver dto  = DtoEntityHelperClass.getResponseDriverObj();

        DriverEntity entity = DtoEntityHelperClass.getDriverEntity();
        when(driverRepository.findByKeyIdAndKeyCountryAndKeyStateAndKeyCityAndKeyPostalCode(entity.getKey().getId(),
                entity.getKey().getCountry(),
                entity.getKey().getState(),
                entity.getKey().getCity(),
                entity.getKey().getPostalCode())).thenReturn(Optional.of(entity));
        when(driverRepository.save(entity)).thenReturn(entity);

        driverService.updateAvailability(entity.getKey(), true);

        verify(driverRepository, times(1)).findByKeyIdAndKeyCountryAndKeyStateAndKeyCityAndKeyPostalCode(entity.getKey().getId(),
                entity.getKey().getCountry(),
                entity.getKey().getState(),
                entity.getKey().getCity(),
                entity.getKey().getPostalCode());

    }

}
