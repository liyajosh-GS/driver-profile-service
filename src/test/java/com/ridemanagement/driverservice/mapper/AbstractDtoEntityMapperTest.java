package com.ridemanagement.driverservice.mapper;

import com.ridemanagement.driverservice.DtoEntityHelperClass;
import com.ridemanagement.driverservice.dto.Document;
import com.ridemanagement.driverservice.dto.Driver;
import com.ridemanagement.driverservice.dto.Vehicle;
import com.ridemanagement.driverservice.entity.DocumentEntity;
import com.ridemanagement.driverservice.entity.DriverEntity;
import com.ridemanagement.driverservice.entity.VehicleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AbstractDtoEntityMapperTest {

    @InjectMocks
    private AbstractDtoEntityMapper<Vehicle, VehicleEntity> abstractDtoEntityMapper = new VehicleDtoEntityMapper();

    @InjectMocks
    private AbstractDtoEntityMapper<Driver, DriverEntity> driverDtoMapper = new DriverDtoEntityMapper();

    @InjectMocks
    private AbstractDtoEntityMapper<Document, DocumentEntity> documentDtoMapper = new DocumentDtoEntityMapper();

    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConvertToDto() {
        VehicleEntity entity = DtoEntityHelperClass.getVehicleEntity();
        Vehicle vehicle = DtoEntityHelperClass.getRequestVehicleObj();

        when(modelMapper.map(entity, Vehicle.class)).thenReturn(vehicle);
        Vehicle result = abstractDtoEntityMapper.convertToDto(entity);

        assertNotNull(result);
        assertEquals(vehicle.getId(), result.getId());
        assertEquals(vehicle.getVehicleType(), result.getVehicleType());
    }

    @Test
    public void testDriverConvertToDto() {
        DriverEntity entity = DtoEntityHelperClass.getDriverEntity();
        Driver dto = DtoEntityHelperClass.getRequestDriverObj();

        when(modelMapper.map(entity, Driver.class)).thenReturn(dto);
        Driver result = driverDtoMapper.convertToDto(entity);

        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getCity(), result.getCity());
    }

    @Test
    public void testDocumentConvertToDto() {
        DocumentEntity entity = DtoEntityHelperClass.getDocumentEntity();
        Document dto = DtoEntityHelperClass.getRequestDocument();

        when(modelMapper.map(entity, Document.class)).thenReturn(dto);
        Document result = documentDtoMapper.convertToDto(entity);

        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getDocumentType(), result.getDocumentType());
    }

    @Test
    public void testConvertToEntity() {
        VehicleEntity entity = DtoEntityHelperClass.getVehicleEntity();
        Vehicle vehicle = DtoEntityHelperClass.getRequestVehicleObj();

        when(modelMapper.map(vehicle, VehicleEntity.class)).thenReturn(entity);
        VehicleEntity result = abstractDtoEntityMapper.convertToEntity(vehicle);

        assertNotNull(result);
        assertEquals(entity.getKey().getId(), vehicle.getId());
        assertEquals(entity.getKey().getVehicleType(), vehicle.getVehicleType());
    }

    @Test
    public void testDriverConvertToEntity() {
        DriverEntity entity = DtoEntityHelperClass.getDriverEntity();
        Driver dto = DtoEntityHelperClass.getResponseDriverObj();

        when(modelMapper.map(dto, DriverEntity.class)).thenReturn(entity);
        DriverEntity result = driverDtoMapper.convertToEntity(dto);

        assertNotNull(result);
        assertEquals(entity.getKey().getId(), dto.getId());
        assertEquals(entity.getKey().getCity(), dto.getCity());
    }

    @Test
    public void testDocumentConvertToEntity() {
        DocumentEntity entity = DtoEntityHelperClass.getDocumentEntity();
        Document dto = DtoEntityHelperClass.getRequestDocument();
        dto.setDocument(mock(MultipartFile.class));

        when(modelMapper.map(dto, DocumentEntity.class)).thenReturn(entity);
        DocumentEntity result = documentDtoMapper.convertToEntity(dto);

        assertNotNull(result);
        assertEquals(entity.getKey().getId(), dto.getId());
        assertEquals(entity.getKey().getOwnerId(), dto.getOwnerId());
    }
}
