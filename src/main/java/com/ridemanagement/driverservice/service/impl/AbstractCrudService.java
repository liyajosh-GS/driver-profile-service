package com.ridemanagement.driverservice.service.impl;

import com.ridemanagement.driverservice.dto.AbstractDto;
import com.ridemanagement.driverservice.entity.AbstractEntity;
import com.ridemanagement.driverservice.entity.AbstractKey;
import com.ridemanagement.driverservice.mapper.DtoEntityMapper;
import com.ridemanagement.driverservice.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class AbstractCrudService <D extends AbstractDto, K extends AbstractKey, E extends AbstractEntity>  implements CrudService <D, K, E> {

    @Autowired
    private final CassandraRepository<E, K> repository;

    @Autowired
    DtoEntityMapper<D, E> mapper;

    protected AbstractCrudService(CassandraRepository<E, K> repository, DtoEntityMapper<D, E> mapper)  {
        this.repository = repository;
        this.mapper = mapper;
    }

    protected abstract E beforeOnSaveEntity(E e);

    protected abstract D readByKey(K k);

    protected abstract void deleteByKey(K k);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public D create(D dto) {
        dto.setId(UUID.randomUUID());
        dto.setCreatedTime(OffsetDateTime.now());
        E entity = mapper.convertToEntity(dto);
        entity = beforeOnSaveEntity(entity);
        E createdEntity = repository.save(entity);
        return mapper.convertToDto(createdEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public D update(D dto) {
        dto.setUpdateTime(OffsetDateTime.now());
        E entity = mapper.convertToEntity(dto);
        entity = beforeOnSaveEntity(entity);
        E updatedEntity = repository.save(entity);
        return mapper.convertToDto(updatedEntity);
    }

    @Override
    public D read(K key) {
        return readByKey(key);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(K key) {
        deleteByKey(key);
    }

    @Override
    public List<D> readAll() {
        List<E> entities = repository.findAll();
        return entities.stream().map((entity) -> (D)mapper.convertToDto(entity)).collect(Collectors.toList());
    }

}
