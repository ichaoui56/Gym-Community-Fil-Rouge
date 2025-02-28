package org.filrouge.gymcommunity.mapper;

import org.mapstruct.*;

@MapperConfig(componentModel = "spring",   unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface GenericMapper<T , RES , REQ>{
    RES toResponseDTO(T entity);
    T fromRequestDTO(REQ dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(REQ dto, @MappingTarget T entity);
}