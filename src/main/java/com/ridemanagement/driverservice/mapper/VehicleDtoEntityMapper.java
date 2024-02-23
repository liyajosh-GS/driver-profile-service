package com.ridemanagement.driverservice.mapper;

import com.ridemanagement.driverservice.dto.Vehicle;
import com.ridemanagement.driverservice.entity.VehicleEntity;
import com.ridemanagement.driverservice.entity.VehicleKey;
import org.springframework.stereotype.Component;

@Component
public class VehicleDtoEntityMapper extends AbstractDtoEntityMapper<Vehicle, VehicleEntity>{
    @Override
    protected Class<Vehicle> getDtoClass() {
        return Vehicle.class;
    }

    @Override
    protected Class<VehicleEntity> getEntityClass() {
        return VehicleEntity.class;
    }

    @Override
    protected VehicleEntity updateKeyForEntity(VehicleEntity vehicleEntity, Vehicle vehicle) {
        VehicleKey key = VehicleKey.builder()
                .vehicleType(vehicle.getVehicleType())
                .vehicleModel(vehicle.getVehicleModel())
                .build();
        vehicleEntity.setKey(key);
        return vehicleEntity;
    }

    @Override
    protected Vehicle updateKeyForDto(Vehicle vehicle, VehicleEntity vehicleEntity) {
        vehicle.setId(vehicleEntity.getKey().getId());
        vehicle.setVehicleType(vehicleEntity.getKey().getVehicleType());
        vehicle.setVehicleModel(vehicleEntity.getKey().getVehicleModel());
        return vehicle;
    }
}
