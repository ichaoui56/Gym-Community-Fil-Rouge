package org.filrouge.gymcommunity.mapper;


import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface GenericMapper<T , RES , REQ>{
    RES toResponseDTO(T entity);
    T fromRequestDTO(REQ dto);

    List<RES> toResponseDTOs(List<T> entities);
    List<T> fromRequestDTOs(List<REQ> dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(REQ dto, @MappingTarget T entity);
}