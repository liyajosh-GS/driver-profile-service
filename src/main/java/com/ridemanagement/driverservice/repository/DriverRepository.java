package com.ridemanagement.driverservice.repository;

import com.ridemanagement.driverservice.entity.DriverEntity;
import com.ridemanagement.driverservice.entity.DriverKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DriverRepository extends CassandraRepository<DriverEntity, DriverKey> {

    Optional<DriverEntity> findByKeyIdAndKeyCountryAndKeyStateAndKeyCityAndKeyPostalCode(UUID id, String country, String state, String city, String postalCode);

    void deleteByKeyIdAndKeyCountryAndKeyStateAndKeyCity(UUID id, String country, String state, String city);

    Optional<DriverEntity> findByKeyId(UUID id);

    void deleteByKeyId(UUID id);
}
