package com.ridemanagement.driverservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractDto {

    private UUID id;

    @JsonIgnore
    OffsetDateTime createdTime;

    @JsonProperty
    public OffsetDateTime getCreatedTime(){
        return createdTime;
    }


    @JsonIgnore
    OffsetDateTime updateTime;

    @JsonProperty
    public OffsetDateTime getUpdateTime(){
        return updateTime;
    }
}
