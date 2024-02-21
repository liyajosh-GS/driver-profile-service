package com.ridemanagement.driverservice.entity;

import com.ridemanagement.driverservice.entity.AbstractEntity;
import com.ridemanagement.driverservice.entity.VerificationStatus;
import lombok.*;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("driver")
public class DriverEntity extends AbstractEntity {

    @PrimaryKey
    private DriverKey key;

    @CassandraType(type = CassandraType.Name.TEXT)
    @Column("name")
    private String name;

    @CassandraType(type = CassandraType.Name.TEXT)
    @Column("phone_country_code")
    private String phoneCountryCode;

    @CassandraType(type = CassandraType.Name.TEXT)
    @Column("phone_number")
    private String phoneNumber;

    @CassandraType(type = CassandraType.Name.TEXT)
    @Column("email")
    private String email;

    @CassandraType(type = CassandraType.Name.DATE)
    @Column("dob")
    private LocalDate dob;

    @CassandraType(type = CassandraType.Name.TEXT)
    @Column("address_line")
    private String addressLine;

    @CassandraType(type = CassandraType.Name.INT)
    @Column("verification_status")
    private VerificationStatus verificationStatus;

    @CassandraType(type = CassandraType.Name.DOUBLE)
    @Column("rating")
    private double rating;

}
