package com.ridemanagement.driverservice.service.impl;

import com.ridemanagement.driverservice.DtoEntityHelperClass;
import com.ridemanagement.driverservice.dto.Document;
import com.ridemanagement.driverservice.entity.DocumentEntity;
import com.ridemanagement.driverservice.entity.DocumentKey;
import com.ridemanagement.driverservice.entity.DocumentType;
import com.ridemanagement.driverservice.mapper.DocumentDtoEntityMapper;
import com.ridemanagement.driverservice.mapper.DtoEntityMapper;
import com.ridemanagement.driverservice.repository.DocumentRepository;
import com.ridemanagement.driverservice.service.async.verification.DocumentVerificationService;
import com.ridemanagement.driverservice.service.async.verification.VerificationServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class AbstractCrudServiceTest {

    @Mock
    private CassandraRepository repository;

    @Mock
    private DtoEntityMapper mapper;

    @Mock
    DocumentRepository documentRepository;

    @Mock
    DocumentDtoEntityMapper documentDtoEntityMapper;

    @Mock
    VerificationServiceFactory verificationServiceFactory;

    @InjectMocks
    private AbstractCrudService abstractCrudService = new DocumentService(mock(DocumentRepository.class), mock(DocumentDtoEntityMapper.class) );


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCreate() {
        Document dto  = DtoEntityHelperClass.getRequestDocument();

        DocumentEntity entity = DtoEntityHelperClass.getDocumentEntity();

        when(documentDtoEntityMapper.convertToEntity(dto)).thenReturn(entity);
        when(documentDtoEntityMapper.convertToDto(entity)).thenReturn(dto);
        when(documentRepository.save(entity)).thenReturn(entity);
        when(verificationServiceFactory.getService(DocumentType.ID)).thenReturn(mock(DocumentVerificationService.class));

        Document createdDto = (Document) abstractCrudService.create(dto);

        verify(documentDtoEntityMapper, times(1)).convertToEntity(dto);

    }

    @Test
    public void testUpdate() {
        Document dto  = DtoEntityHelperClass.getRequestDocument();

        DocumentEntity entity = DtoEntityHelperClass.getDocumentEntity();

        when(documentDtoEntityMapper.convertToEntity(dto)).thenReturn(entity);
        when(documentDtoEntityMapper.convertToDto(entity)).thenReturn(dto);
        when(documentRepository.save(entity)).thenReturn(entity);
        when(verificationServiceFactory.getService(DocumentType.ID)).thenReturn(mock(DocumentVerificationService.class));

        Document createdDto = (Document) abstractCrudService.update(dto);

        verify(documentDtoEntityMapper, times(1)).convertToEntity(dto);

    }

    @Test
    public void testRead() {
        Document dto  = DtoEntityHelperClass.getRequestDocument();

        DocumentEntity entity = DtoEntityHelperClass.getDocumentEntity();
        DocumentKey key = DtoEntityHelperClass.getDocumentKey();

        when(documentDtoEntityMapper.convertToEntity(dto)).thenReturn(entity);
        when(documentDtoEntityMapper.convertToDto(entity)).thenReturn(dto);
        when(documentRepository.findByKeyIdAndKeyOwnerId(key.getId(), key.getOwnerId())).thenReturn(Optional.of(entity));

        Document readResponse = (Document) abstractCrudService.read(key);
        assertNotNull(readResponse);
        // its a read
        verify(documentDtoEntityMapper, times(0)).convertToEntity(dto);

    }

    @Test
    public void testDelete() {
        Document dto  = DtoEntityHelperClass.getRequestDocument();

        DocumentEntity entity = DtoEntityHelperClass.getDocumentEntity();
        DocumentKey key = DtoEntityHelperClass.getDocumentKey();

        when(documentDtoEntityMapper.convertToEntity(dto)).thenReturn(entity);
        when(documentDtoEntityMapper.convertToDto(entity)).thenReturn(dto);
        doNothing().when(documentRepository).deleteByKeyIdAndKeyOwnerId(key.getId(), key.getOwnerId());

       abstractCrudService.delete(key);
        // its a read
        verify(documentDtoEntityMapper, times(0)).convertToEntity(dto);

    }
}
