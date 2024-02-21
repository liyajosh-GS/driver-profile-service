package com.ridemanagement.driverservice.service;


import com.ridemanagement.driverservice.dto.AbstractDto;
import com.ridemanagement.driverservice.entity.AbstractEntity;
import com.ridemanagement.driverservice.entity.AbstractKey;

import java.util.List;

public interface CrudService <D extends AbstractDto, K extends AbstractKey, E extends AbstractEntity> {

    D create(D dto);

    D read(K key);

    D update(D dto);

    void delete(K key);

    List<D> readAll();
}
