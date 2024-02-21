package com.ridemanagement.driverservice.controller;

import com.ridemanagement.driverservice.dto.Driver;
import com.ridemanagement.driverservice.dto.Vehicle;
import com.ridemanagement.driverservice.entity.DriverKey;
import com.ridemanagement.driverservice.entity.VehicleKey;
import com.ridemanagement.driverservice.entity.VehicleModel;
import com.ridemanagement.driverservice.entity.VehicleType;
import com.ridemanagement.driverservice.service.impl.DriverService;
import com.ridemanagement.driverservice.service.impl.VehicleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> create(@RequestBody @Valid Vehicle vehicleRequest){
        Vehicle vehicle = vehicleService.create(vehicleRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vehicle);
    }

    @PutMapping
    public ResponseEntity<Vehicle> update(@RequestBody @Valid Vehicle vehicleRequest){
        Vehicle vehicle = vehicleService.update(vehicleRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(vehicle);
    }

    @GetMapping("/{id}/{vehicleType}/{vehicleModel}")
    public ResponseEntity<Vehicle> getByKey(@PathVariable @NotNull UUID id,
                                               @PathVariable @NotNull @NotEmpty VehicleType vehicleType,
                                               @PathVariable @NotNull @NotEmpty VehicleModel vehicleModel){

        return ResponseEntity.status(HttpStatus.OK)
                .body(vehicleService.read(buildKey(id, vehicleType, vehicleModel)));
    }

    @DeleteMapping("/{id}/{vehicleType}/{vehicleModel}")
    public ResponseEntity<Vehicle> deleteByKey(@PathVariable @NotNull UUID id,
                                                @PathVariable @NotNull @NotEmpty VehicleType vehicleType,
                                                @PathVariable @NotNull @NotEmpty VehicleModel vehicleModel){

        vehicleService.delete(buildKey(id, vehicleType, vehicleModel));
        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }

    private VehicleKey buildKey(UUID id,
                                VehicleType type,
                                VehicleModel model){
        return VehicleKey.builder()
                .id(id)
                .vehicleType(type)
                .vehicleModel(model)
                .build();
    }
}
