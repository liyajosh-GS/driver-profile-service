package com.ridemanagement.driverservice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("document")
public class DocumentEntity extends AbstractEntity{

    @PrimaryKey
    private DocumentKey key;

    @CassandraType(type = CassandraType.Name.INT)
    @Column("document_type")
    private DocumentType documentType;

    @CassandraType(type = CassandraType.Name.INT)
    @Column("verification_status")
    private VerificationStatus verificationStatus;

    @CassandraType(type = CassandraType.Name.BLOB)
    @Column("document")
    private byte[] document;
}
