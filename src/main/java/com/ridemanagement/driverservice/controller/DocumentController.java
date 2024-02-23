package com.ridemanagement.driverservice.controller;


import com.ridemanagement.driverservice.dto.Document;
import com.ridemanagement.driverservice.entity.DocumentKey;
import com.ridemanagement.driverservice.entity.DocumentType;
import com.ridemanagement.driverservice.service.impl.DocumentService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @PostMapping("/{ownerId}/{documentType}")
    public ResponseEntity<Document> upload(@RequestParam("document") MultipartFile document,
                                             @PathVariable @NotNull UUID ownerId,
                                             @PathVariable @NotNull DocumentType documentType){
        Document documentRequest = Document.builder()
                .ownerId(ownerId)
                .documentType(documentType)
                .document(document)
                .build();
        Document response = documentService.create(documentRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping("/{id}/{ownerId}/{documentType}")
    public ResponseEntity<Document> update(@RequestParam("document") MultipartFile document,
                                           @PathVariable @NotNull UUID id,
                                           @PathVariable @NotNull UUID ownerId,
                                           @PathVariable @NotNull DocumentType documentType
                                           ){
        Document documentRequest = Document.builder()
                .ownerId(ownerId)
                .documentType(documentType)
                .document(document)
                .build();
        documentRequest.setId(id);
        Document response = documentService.update(documentRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}/{ownerId}")
    public ResponseEntity<Document> getDocument(@PathVariable @NotNull UUID id,
                                           @PathVariable @NotNull UUID ownerId){
        DocumentKey documentKey = DocumentKey.builder()
                .ownerId(ownerId)
                .build();
        documentKey.setId(id);
        Document response = documentService.read(documentKey);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}/{ownerId}")
    public ResponseEntity<Void> deleteDocument(@PathVariable @NotNull UUID id,
                                                @PathVariable @NotNull UUID ownerId){
        DocumentKey documentKey = DocumentKey.builder()
                .ownerId(ownerId)
                .build();
        documentKey.setId(id);
        documentService.delete(documentKey);
        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }
}
