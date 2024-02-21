package com.ridemanagement.driverservice.service.impl;

import com.ridemanagement.driverservice.dto.Driver;
import com.ridemanagement.driverservice.entity.DriverKey;
import com.ridemanagement.driverservice.entity.VerificationStatus;
import com.ridemanagement.driverservice.entity.DriverEntity;
import com.ridemanagement.driverservice.exception.NoSuchItemFoundException;
import com.ridemanagement.driverservice.mapper.DriverDtoEntityMapper;
import com.ridemanagement.driverservice.mapper.DtoEntityMapper;
import com.ridemanagement.driverservice.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DriverService extends AbstractCrudService <Driver, DriverKey, DriverEntity> {

    @Autowired
    DriverRepository repository;

    @Autowired
    DriverDtoEntityMapper mapper;

    protected DriverService(CassandraRepository<DriverEntity, DriverKey> repository, DtoEntityMapper<Driver, DriverEntity> mapper) {
        super(repository, mapper);
    }

    public Driver getDriverById (UUID id){
        return repository.findByKeyId(id)
                .map(e -> mapper.convertToDto(e)).orElseThrow(() -> new NoSuchItemFoundException("Driver does not exist"));
    }

    public void deleteDriverById (UUID id){
        repository.deleteByKeyId(id);
        return;
    }

    @Override
    protected DriverEntity beforeOnSaveEntity(DriverEntity driverEntity) {
        driverEntity.setVerificationStatus(VerificationStatus.PENDING);
        return driverEntity;
    }

    public Driver getDriverByCity (UUID id, String country, String state, String city){
        return repository.findByKeyIdAndKeyCountryAndKeyStateAndKeyCity(id, country, state, city)
                .map(e -> mapper.convertToDto(e)).orElseThrow(() -> new NoSuchItemFoundException("Driver does not exist"));
    }

    @Override
    protected Driver readByKey(DriverKey driverKey) {
        return getDriverByCity(driverKey.getId(),
                driverKey.getCountry(),
                driverKey.getState(),
                driverKey.getCity());
    }

    @Override
    protected void deleteByKey(DriverKey driverKey) {
        repository.deleteByKeyIdAndKeyCountryAndKeyStateAndKeyCity(driverKey.getId(),
                driverKey.getCountry(),
                driverKey.getState(),
                driverKey.getCity());
    }

}
