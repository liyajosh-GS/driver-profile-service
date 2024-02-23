package com.ridemanagement.driverservice.controller;

import com.ridemanagement.driverservice.DtoEntityHelperClass;
import com.ridemanagement.driverservice.dto.Driver;
import com.ridemanagement.driverservice.entity.DriverKey;
import com.ridemanagement.driverservice.service.impl.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class DriverControllerTest {

    @InjectMocks
    private DriverController controller;

    @Mock
    private DriverService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void testCreateDriver() {
        Driver dto = DtoEntityHelperClass.getRequestDriverObj();

        when(service.create(dto)).thenReturn(dto);

        ResponseEntity responseEntity = controller.create(dto);

        verify(service, times(1)).create(dto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(dto, responseEntity.getBody());
    }

    @Test
    public void testUpdateDriver() {
        Driver dto = DtoEntityHelperClass.getRequestDriverObj();

        when(service.update(dto)).thenReturn(dto);

        ResponseEntity responseEntity = controller.update(dto);

        verify(service, times(1)).update(dto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dto, responseEntity.getBody());
    }

    @Test
    public void testReadDriver() {
        Driver dto = DtoEntityHelperClass.getRequestDriverObj();
        Driver responseDriverObj = DtoEntityHelperClass.getResponseDriverObj();
        DriverKey driverKey = DtoEntityHelperClass.getDriverKey();



        when(service.read(driverKey)).thenReturn(responseDriverObj);

        ResponseEntity responseEntity = controller.getByKey(driverKey.getId(),
                driverKey.getCountry(),
                driverKey.getState(),
                driverKey.getCity(),
                driverKey.getPostalCode());

        verify(service, times(1)).read(driverKey);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dto, responseEntity.getBody());
    }

    @Test
    public void testUpdateAvailability() {
        Driver dto = DtoEntityHelperClass.getRequestDriverObj();
        Driver responseDriverObj = DtoEntityHelperClass.getResponseDriverObj();
        DriverKey driverKey = DtoEntityHelperClass.getDriverKey();

        doNothing().when(service).updateAvailability(driverKey, true);

        ResponseEntity responseEntity = controller.updateAvailability(driverKey.getId(),
                driverKey.getCountry(),
                driverKey.getState(),
                driverKey.getCity(),
                driverKey.getPostalCode(),
                true);

        verify(service, times(1)).updateAvailability(driverKey, true);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testReadDriverById() {
        Driver dto = DtoEntityHelperClass.getRequestDriverObj();
        Driver responseDriverObj = DtoEntityHelperClass.getResponseDriverObj();
        DriverKey driverKey = DtoEntityHelperClass.getDriverKey();



        when(service.getDriverById(driverKey.getId())).thenReturn(responseDriverObj);

        ResponseEntity responseEntity = controller.getById(
                driverKey.getId());

        verify(service, times(1)).getDriverById(driverKey.getId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dto, responseEntity.getBody());
    }

    @Test
    public void testDeleteDriverById() {
        Driver dto = DtoEntityHelperClass.getRequestDriverObj();
        Driver responseDriverObj = DtoEntityHelperClass.getResponseDriverObj();
        DriverKey driverKey = DtoEntityHelperClass.getDriverKey();

        doNothing().when(service).deleteDriverById(driverKey.getId());

        ResponseEntity responseEntity = controller.delete(driverKey.getId());

        verify(service, times(1)).deleteDriverById(driverKey.getId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }
}
