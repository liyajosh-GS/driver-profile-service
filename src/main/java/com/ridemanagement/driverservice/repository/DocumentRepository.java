package com.ridemanagement.driverservice.repository;

import com.ridemanagement.driverservice.entity.DocumentEntity;
import com.ridemanagement.driverservice.entity.DocumentKey;
import com.ridemanagement.driverservice.entity.DriverEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends CassandraRepository<DocumentEntity, DocumentKey> {

    Optional<DocumentEntity> findByKeyIdAndKeyDriverId(UUID id, UUID driverId);

    void deleteByKeyIdAndKeyDriverId(UUID id, UUID driverId);

}
