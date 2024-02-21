package com.ridemanagement.driverservice.repository;

import com.ridemanagement.driverservice.entity.*;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleRepository extends CassandraRepository<VehicleEntity, VehicleKey> {

    Optional<VehicleEntity> findByKeyIdAndKeyVehicleTypeAndKeyVehicleModel(UUID id, VehicleType vehicleType, VehicleModel vehicleModel);

    void deleteByKeyIdAndKeyVehicleTypeAndKeyVehicleModel(UUID id, VehicleType vehicleType, VehicleModel vehicleModel);

}
