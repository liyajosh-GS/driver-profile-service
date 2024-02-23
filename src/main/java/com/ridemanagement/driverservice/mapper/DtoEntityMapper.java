package com.ridemanagement.driverservice.mapper;

import com.ridemanagement.driverservice.dto.AbstractDto;
import com.ridemanagement.driverservice.entity.AbstractEntity;

public interface DtoEntityMapper<D extends AbstractDto, E extends AbstractEntity> {

    D convertToDto(E entity);

    E convertToEntity(D dto);

}