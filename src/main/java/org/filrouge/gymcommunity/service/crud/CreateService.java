package org.filrouge.gymcommunity.service.crud;

import org.filrouge.gymcommunity.service.base.BaseService;

public interface CreateService<
        RES,
        REQ,
        T,
        ID>
        extends BaseService<RES, REQ, T, ID> {

    default RES create(REQ requestDTO) {
        T entity = getMapper().fromRequestDTO(requestDTO);
        T savedEntity = getRepository().save(entity);
        return getMapper().toResponseDTO(savedEntity);
    }
}
