package com.ridemanagement.driverservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("vehicle")
public class VehicleEntity extends AbstractEntity{

    @PrimaryKey
    private VehicleKey key;

    @CassandraType(type = CassandraType.Name.TEXT)
    @Column("owned_by")
    private String ownedBy;

    @CassandraType(type = CassandraType.Name.BOOLEAN)
    @Column("rented")
    private boolean rented;

    @CassandraType(type = CassandraType.Name.TEXT)
    @Column("registration_id")
    private String registrationId;

    @CassandraType(type = CassandraType.Name.INT)
    @Column("seating_capacity")
    private int seatingCapacity;

    @CassandraType(type = CassandraType.Name.INT)
    @Column("verification_status")
    private VerificationStatus verificationStatus;

}
