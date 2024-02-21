package com.ridemanagement.driverservice.mapper;

import com.ridemanagement.driverservice.dto.AbstractDto;
import com.ridemanagement.driverservice.entity.AbstractEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDtoEntityMapper <D extends AbstractDto, E extends AbstractEntity>  implements DtoEntityMapper<D, E> {

    @Autowired
    ModelMapper modelMapper;
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    protected abstract Class<D> getDtoClass();
    protected abstract Class<E> getEntityClass();

    protected abstract E updateKeyForEntity(E e, D d);

    protected abstract D updateKeyForDto(D d, E e);
    public AbstractDtoEntityMapper() {
        this.dtoClass = getDtoClass();
        this.entityClass = getEntityClass();
    }

    @Override
    public D convertToDto(E entity) {
        D dto = modelMapper.map(entity, dtoClass);
        dto = updateKeyForDto(dto, entity);
        return dto;
    }
    @Override
    public E convertToEntity(D dto) {
        E entity = modelMapper.map(dto, entityClass);
        entity = updateKeyForEntity(entity, dto);
        return entity;
    }
}
