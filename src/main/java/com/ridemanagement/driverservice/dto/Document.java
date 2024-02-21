package com.ridemanagement.driverservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ridemanagement.driverservice.entity.DocumentType;
import com.ridemanagement.driverservice.entity.VerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document extends AbstractDto {

    private UUID ownerId;

    private DocumentType documentType;

    @JsonIgnore
    private VerificationStatus verificationStatus;

    @JsonProperty
    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    private MultipartFile document;
}
