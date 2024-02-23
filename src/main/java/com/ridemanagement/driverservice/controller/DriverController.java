package com.ridemanagement.driverservice.controller;

import com.ridemanagement.driverservice.dto.Driver;
import com.ridemanagement.driverservice.entity.DriverKey;
import com.ridemanagement.driverservice.service.impl.DriverService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    DriverService driverService;

    @PostMapping
    public ResponseEntity<Driver> create(@RequestBody @Valid Driver driverRequest){
        Driver driver = driverService.create(driverRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(driver);
    }

    @PutMapping
    public ResponseEntity<Driver> update(@RequestBody @Valid Driver driverRequest){
        Driver driver = driverService.update(driverRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(driver);
    }

    @GetMapping("/{id}/{country}/{state}/{city}/{postalCode}")
    public ResponseEntity<Driver> getByKey(@PathVariable @NotNull UUID id,
                                      @PathVariable @NotNull @NotEmpty String country,
                                      @PathVariable @NotNull @NotEmpty String state,
                                      @PathVariable @NotNull @NotEmpty String city, @PathVariable @NotNull @NotEmpty String postalCode){

        return ResponseEntity.status(HttpStatus.OK)
                .body(driverService.read(buildKey(id, country, state, city, postalCode)));
    }

    @PatchMapping("/{id}/{country}/{state}/{city}/{postalCode}")
    public ResponseEntity<Void> updateAvailability(@PathVariable @NotNull UUID id,
                                            @PathVariable @NotNull @NotEmpty String country,
                                            @PathVariable @NotNull @NotEmpty String state,
                                            @PathVariable @NotNull @NotEmpty String city, @PathVariable @NotNull @NotEmpty String postalCode,
                                                     @RequestBody @Valid boolean isAvailable) {

        driverService.updateAvailability(buildKey(id, country, state, city, postalCode), isAvailable);
        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getById(@PathVariable @NotNull UUID id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(driverService.getDriverById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Driver> delete(@PathVariable @NotNull UUID id){
        driverService.deleteDriverById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }



    private DriverKey buildKey(UUID id,
                               String country,
                               String state,
                               String city,
                               String postalCode){
        return DriverKey.builder()
                .id(id)
                .country(country)
                .state(state)
                .city(city)
                .postalCode(postalCode)
                .build();
    }

}
