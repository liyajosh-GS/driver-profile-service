package com.ridemanagement.driverservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractDto {

    private UUID id;

    @JsonIgnore
    Instant createdTime;

    @JsonProperty
    public Instant getCreatedTime(){
        return createdTime;
    }

    @JsonIgnore
    Instant updateTime;

    @JsonProperty
    public Instant getUpdateTime(){
        return updateTime;
    }
}
