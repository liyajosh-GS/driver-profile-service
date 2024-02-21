package com.ridemanagement.driverservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ridemanagement.driverservice.entity.VehicleModel;
import com.ridemanagement.driverservice.entity.VehicleType;
import com.ridemanagement.driverservice.entity.VerificationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends AbstractDto{

    @NotNull
    private VehicleType vehicleType;

    @NotNull
    private VehicleModel vehicleModel;

    @NotNull
    private String ownedBy;

    @NotNull
    private boolean rented;

    @NotNull
    private String registrationId;

    @NotNull
    private int seatingCapacity;

    @JsonIgnore
    private VerificationStatus verificationStatus;

    @JsonProperty
    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }
}
