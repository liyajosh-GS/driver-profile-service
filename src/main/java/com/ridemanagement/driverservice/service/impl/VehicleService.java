package com.ridemanagement.driverservice.service.impl;

import com.ridemanagement.driverservice.dto.Document;
import com.ridemanagement.driverservice.dto.Driver;
import com.ridemanagement.driverservice.dto.Vehicle;
import com.ridemanagement.driverservice.entity.*;
import com.ridemanagement.driverservice.exception.NoSuchItemFoundException;
import com.ridemanagement.driverservice.mapper.DriverDtoEntityMapper;
import com.ridemanagement.driverservice.mapper.DtoEntityMapper;
import com.ridemanagement.driverservice.mapper.VehicleDtoEntityMapper;
import com.ridemanagement.driverservice.repository.DriverRepository;
import com.ridemanagement.driverservice.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VehicleService extends AbstractCrudService<Vehicle, VehicleKey, VehicleEntity> {

    @Autowired
    VehicleRepository repository;

    @Autowired
    VehicleDtoEntityMapper mapper;

    protected VehicleService(CassandraRepository<VehicleEntity, VehicleKey> repository, DtoEntityMapper<Vehicle, VehicleEntity> mapper) {
        super(repository, mapper);
    }

    @Override
    protected VehicleEntity beforeOnSaveEntity(VehicleEntity vehicleEntity) {
        vehicleEntity.setVerificationStatus(VerificationStatus.PENDING);
        return vehicleEntity;
    }

    public Vehicle getVehicleByKey (UUID id, VehicleType vehicleType, VehicleModel vehicleModel){
        return repository.findByKeyIdAndKeyVehicleTypeAndKeyVehicleModel(id, vehicleType, vehicleModel)
                .map(e -> mapper.convertToDto(e)).orElseThrow(() -> new NoSuchItemFoundException("Vehicle does not exist"));
    }

    @Override
    protected Vehicle readByKey(VehicleKey vehicleKey) {
        return getVehicleByKey(vehicleKey.getId(), vehicleKey.getVehicleType(), vehicleKey.getVehicleModel());
    }

    @Override
    protected void deleteByKey(VehicleKey vehicleKey) {
        repository.deleteByKeyIdAndKeyVehicleTypeAndKeyVehicleModel(vehicleKey.getId(), vehicleKey.getVehicleType(), vehicleKey.getVehicleModel());
    }
}
