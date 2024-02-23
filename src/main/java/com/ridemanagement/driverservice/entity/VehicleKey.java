package com.ridemanagement.driverservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyClass
public class VehicleKey extends AbstractKey{

    @PrimaryKeyColumn(name = "vehicle_type", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    @CassandraType(type = CassandraType.Name.INT)
    private VehicleType vehicleType;

    @PrimaryKeyColumn(name = "vehicle_model", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    @CassandraType(type = CassandraType.Name.INT)
    private VehicleModel vehicleModel;

}
