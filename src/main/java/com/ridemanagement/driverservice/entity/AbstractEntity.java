package com.ridemanagement.driverservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;

import java.io.Serializable;
import java.time.Instant;

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
