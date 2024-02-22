package com.ridemanagement.driverservice.mapper;

import com.ridemanagement.driverservice.dto.AbstractDto;
import com.ridemanagement.driverservice.entity.AbstractEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZoneOffset;

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
//        dto.setCreatedTime(entity.getCreatedTime().atOffset(ZoneOffset.UTC));
//        if(entity.getUpdateTime() != null){
//            dto.setUpdateTime(entity.getUpdateTime().atOffset(ZoneOffset.UTC));
//        }
        return dto;
    }
    @Override
    public E convertToEntity(D dto) {
        E entity = modelMapper.map(dto, entityClass);
        entity = updateKeyForEntity(entity, dto);
//        entity.setCreatedTime(dto.getCreatedTime().toInstant());
//        if(dto.getUpdateTime() != null){
//            entity.setUpdateTime(dto.getUpdateTime().toInstant());
//        }
        return entity;
    }
}
