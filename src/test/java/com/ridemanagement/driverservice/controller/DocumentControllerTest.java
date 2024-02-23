package com.ridemanagement.driverservice.controller;

import com.ridemanagement.driverservice.DtoEntityHelperClass;
import com.ridemanagement.driverservice.dto.Document;
import com.ridemanagement.driverservice.dto.Driver;
import com.ridemanagement.driverservice.entity.DocumentKey;
import com.ridemanagement.driverservice.entity.DocumentType;
import com.ridemanagement.driverservice.service.impl.DocumentService;
import com.ridemanagement.driverservice.service.impl.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class DocumentControllerTest {

    @InjectMocks
    private DocumentController controller;

    @Mock
    private DocumentService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDocument() {
        Document dto = DtoEntityHelperClass.getRequestDocument();
        dto.setDocument(mock(MultipartFile.class));

        when(service.create(any())).thenReturn(dto);

        ResponseEntity responseEntity = controller.upload(mock(MultipartFile.class), dto.getOwnerId(), DocumentType.ID);

        verify(service, times(1)).create(any());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(dto, responseEntity.getBody());
    }

    @Test
    public void testUpdateDocument() {
        Document dto = DtoEntityHelperClass.getRequestDocument();
        dto.setDocument(mock(MultipartFile.class));
        when(service.update(any())).thenReturn(dto);

        ResponseEntity responseEntity = controller.update(mock(MultipartFile.class),
                dto.getId(),
                dto.getOwnerId(),
                dto.getDocumentType());

        verify(service, times(1)).update(any());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dto, responseEntity.getBody());
    }

    @Test
    public void testReadDocument() {
        DocumentKey key = DtoEntityHelperClass.getDocumentKey();
        Document document = DtoEntityHelperClass.getResponseDocument();

        when(service.read(key)).thenReturn(document);

        ResponseEntity responseEntity = controller.getDocument(key.getId(), key.getOwnerId());

        verify(service, times(1)).read(key);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(document, responseEntity.getBody());
    }

    @Test
    public void testDeleteDocument() {
        DocumentKey key = DtoEntityHelperClass.getDocumentKey();
        Document document = DtoEntityHelperClass.getResponseDocument();

        doNothing().when(service).delete(key);

        ResponseEntity responseEntity = controller.deleteDocument(key.getId(), key.getOwnerId());

        verify(service, times(1)).delete(key);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }
}
