package org.filrouge.gymcommunity.service.crud;

import org.filrouge.gymcommunity.service.base.BaseService;
import org.filrouge.gymcommunity.model.entity.BaseEntity;

public interface CreateService<
        RES,
        REQ,
        T extends BaseEntity<ID>, // Ensure T is a JPA entity
        ID>
        extends BaseService<RES, REQ, T, ID> {

    default RES create(REQ requestDTO) {
        T entity = getMapper().fromRequestDTO(requestDTO);
        T savedEntity = getRepository().save(entity);
        return getMapper().toResponseDTO(savedEntity);
    }
}
