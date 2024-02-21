package com.ridemanagement.driverservice.service.impl;

import com.ridemanagement.driverservice.dto.Document;
import com.ridemanagement.driverservice.dto.Driver;
import com.ridemanagement.driverservice.entity.DocumentEntity;
import com.ridemanagement.driverservice.entity.DocumentKey;
import com.ridemanagement.driverservice.entity.DriverEntity;
import com.ridemanagement.driverservice.entity.VerificationStatus;
import com.ridemanagement.driverservice.exception.NoSuchItemFoundException;
import com.ridemanagement.driverservice.mapper.DocumentDtoEntityMapper;
import com.ridemanagement.driverservice.mapper.DtoEntityMapper;
import com.ridemanagement.driverservice.repository.DocumentRepository;
import com.ridemanagement.driverservice.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DocumentService extends AbstractCrudService <Document, DocumentKey, DocumentEntity> {

    @Autowired
    DocumentRepository repository;

    @Autowired
    DocumentDtoEntityMapper mapper;

    protected DocumentService(CassandraRepository<DocumentEntity, DocumentKey> repository, DtoEntityMapper<Document, DocumentEntity> mapper) {
        super(repository, mapper);
    }

    @Override
    protected DocumentEntity beforeOnSaveEntity(DocumentEntity documentEntity) {
        documentEntity.setVerificationStatus(VerificationStatus.PENDING);
        return documentEntity;
    }

    public Document getDocumentByKey (UUID id, UUID driverId){
        return repository.findByKeyIdAndKeyDriverId(id, driverId)
                .map(e -> mapper.convertToDto(e)).orElseThrow(() -> new NoSuchItemFoundException("Document does not exist"));
    }

    @Override
    protected Document readByKey(DocumentKey documentKey) {
        return getDocumentByKey(documentKey.getId(), documentKey.getOwnerId());
    }

    @Override
    protected void deleteByKey(DocumentKey documentKey) {
        repository.deleteByKeyIdAndKeyDriverId(documentKey.getId(), documentKey.getOwnerId());
        return;
    }
}
