package com.ridemanagement.driverservice.service.impl;

import com.ridemanagement.driverservice.dto.Document;
import com.ridemanagement.driverservice.entity.DocumentEntity;
import com.ridemanagement.driverservice.entity.DocumentKey;
import com.ridemanagement.driverservice.entity.VerificationStatus;
import com.ridemanagement.driverservice.exception.NoSuchItemFoundException;
import com.ridemanagement.driverservice.mapper.DocumentDtoEntityMapper;
import com.ridemanagement.driverservice.mapper.DtoEntityMapper;
import com.ridemanagement.driverservice.repository.DocumentRepository;
import com.ridemanagement.driverservice.service.async.verification.DocumentVerificationService;
import com.ridemanagement.driverservice.service.async.verification.VerificationServiceFactory;
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

    @Autowired
    VerificationServiceFactory verificationServiceFactory;

    protected DocumentService(CassandraRepository<DocumentEntity, DocumentKey> repository, DtoEntityMapper<Document, DocumentEntity> mapper) {
        super(repository, mapper);
    }

    @Override
    protected DocumentEntity beforeOnSaveEntity(DocumentEntity documentEntity) {
        documentEntity.setVerificationStatus(VerificationStatus.PENDING);
        DocumentVerificationService<DocumentKey, Document> verificationService = verificationServiceFactory.getService(documentEntity.getDocumentType());
        //  verificationService.startVerification(documentEntity.getKey());
        return documentEntity;
    }

    public Document getDocumentByKey (UUID id, UUID ownerId){
        return repository.findByKeyIdAndKeyOwnerId(id, ownerId)
                .map(e -> mapper.convertToDto(e)).orElseThrow(() -> new NoSuchItemFoundException("Document does not exist"));
    }

    @Override
    protected Document readByKey(DocumentKey documentKey) {
        return getDocumentByKey(documentKey.getId(), documentKey.getOwnerId());
    }

    @Override
    protected void deleteByKey(DocumentKey documentKey) {
        repository.deleteByKeyIdAndKeyOwnerId(documentKey.getId(), documentKey.getOwnerId());
        return;
    }
}
