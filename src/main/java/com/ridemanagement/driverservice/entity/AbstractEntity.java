package com.ridemanagement.driverservice.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntity implements Serializable {

    @Column("created_time")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    @CreatedDate
    private Instant createdTime;


    @Column("update_time")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    private Instant updateTime;
}
