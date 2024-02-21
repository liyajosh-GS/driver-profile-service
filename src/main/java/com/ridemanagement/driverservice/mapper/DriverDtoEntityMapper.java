package com.ridemanagement.driverservice.mapper;

import com.ridemanagement.driverservice.dto.Driver;
import com.ridemanagement.driverservice.entity.DriverEntity;
import com.ridemanagement.driverservice.entity.DriverKey;
import org.springframework.stereotype.Component;

@Component
public class DriverDtoEntityMapper extends AbstractDtoEntityMapper<Driver, DriverEntity>{
    @Override
    protected Class<Driver> getDtoClass() {
        return Driver.class;
    }

    @Override
    protected Class<DriverEntity> getEntityClass() {
        return DriverEntity.class;
    }

    @Override
    protected DriverEntity updateKeyForEntity(DriverEntity driverEntity, Driver driver) {
        DriverKey key = DriverKey.builder()
                .country(driver.getCountry())
                .state(driver.getState())
                .city(driver.getCity())
                .postalCode(driver.getPostalCode())
                .id(driver.getId())
                .build();
        driverEntity.setKey(key);
        return driverEntity;
    }

    @Override
    protected Driver updateKeyForDto(Driver driver, DriverEntity driverEntity) {
        driver.setId(driverEntity.getKey().getId());
        driver.setCountry(driverEntity.getKey().getCountry());
        driver.setState(driverEntity.getKey().getState());
        driver.setCity(driverEntity.getKey().getCity());
        driver.setPostalCode(driverEntity.getKey().getPostalCode());
        return driver;
    }
}
