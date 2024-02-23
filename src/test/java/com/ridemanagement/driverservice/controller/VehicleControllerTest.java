package com.ridemanagement.driverservice.controller;


import com.ridemanagement.driverservice.DtoEntityHelperClass;
import com.ridemanagement.driverservice.dto.Document;
import com.ridemanagement.driverservice.dto.Vehicle;
import com.ridemanagement.driverservice.entity.DocumentKey;
import com.ridemanagement.driverservice.entity.DocumentType;
import com.ridemanagement.driverservice.entity.VehicleKey;
import com.ridemanagement.driverservice.service.impl.DocumentService;
import com.ridemanagement.driverservice.service.impl.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VehicleControllerTest {

    @InjectMocks
    private VehicleController controller;

    @Mock
    private VehicleService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateVehicle() {
        Vehicle dto = DtoEntityHelperClass.getRequestVehicleObj();
        when(service.create(any())).thenReturn(dto);

        ResponseEntity responseEntity = controller.create(dto);

        verify(service, times(1)).create(any());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(dto, responseEntity.getBody());
    }

    @Test
    public void testUpdateVehicle() {
        Vehicle dto = DtoEntityHelperClass.getRequestVehicleObj();
        when(service.update(any())).thenReturn(dto);

        ResponseEntity responseEntity = controller.update(dto);

        verify(service, times(1)).update(any());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dto, responseEntity.getBody());
    }

    @Test
    public void testReadVehicle() {
        VehicleKey key = DtoEntityHelperClass.getVehicleKey();
        Vehicle vehicle = DtoEntityHelperClass.getRequestVehicleObj();

        when(service.read(key)).thenReturn(vehicle);

        ResponseEntity responseEntity = controller.getByKey(key.getId(), key.getVehicleType(), key.getVehicleModel());

        verify(service, times(1)).read(key);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(vehicle, responseEntity.getBody());
    }

    @Test
    public void testDeleteVehicle() {
        VehicleKey key = DtoEntityHelperClass.getVehicleKey();
        Document document = DtoEntityHelperClass.getResponseDocument();

        doNothing().when(service).delete(key);

        ResponseEntity responseEntity = controller.deleteByKey(key.getId(), key.getVehicleType(), key.getVehicleModel());

        verify(service, times(1)).delete(key);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }
}
