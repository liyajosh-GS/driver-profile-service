package com.ridemanagement.driverservice.mapper;

import com.ridemanagement.driverservice.dto.Document;
import com.ridemanagement.driverservice.entity.DocumentEntity;
import com.ridemanagement.driverservice.entity.DocumentKey;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DocumentDtoEntityMapper extends AbstractDtoEntityMapper<Document, DocumentEntity>{

    @Override
    protected Class<Document> getDtoClass() {
        return Document.class;
    }

    @Override
    protected Class<DocumentEntity> getEntityClass() {
        return DocumentEntity.class;
    }

    private void updateEntity(DocumentEntity documentEntity, Document document) throws IOException {
        documentEntity.setDocument(document.getDocument().getBytes());
    }

    @Override
    protected DocumentEntity updateKeyForEntity(DocumentEntity documentEntity, Document document) {
        DocumentKey key = DocumentKey.builder()
                .id(document.getId())
                .ownerId(document.getOwnerId())
                .build();
        documentEntity.setKey(key);
        try {
            updateEntity(documentEntity, document);
        } catch (IOException exception){
            throw new RuntimeException("Could not process file");
        }
        return documentEntity;
    }

    @Override
    protected Document updateKeyForDto(Document document, DocumentEntity documentEntity) {
        document.setId(documentEntity.getKey().getId());
        document.setOwnerId(documentEntity.getKey().getOwnerId());
        return document;
    }
}
