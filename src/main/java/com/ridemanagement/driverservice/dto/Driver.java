package com.ridemanagement.driverservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ridemanagement.driverservice.entity.VerificationStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends AbstractDto {

    @NotNull
    private String name;

    @NotNull
    private String phoneCountryCode;

    @NotNull
    private String phoneNumber;

    @NotNull
    @Email
    private String email;

    @NotNull
    private LocalDate dob;

    @NotNull
    private String addressLine;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String postalCode;

    @NotNull
    private String country;

    @JsonIgnore
    private VerificationStatus verificationStatus;

    @JsonProperty
    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    @JsonIgnore
    private double rating;

    @JsonProperty
    public double getRating() {
        return rating;
    }

}
