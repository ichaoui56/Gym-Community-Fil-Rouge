package org.filrouge.gymcommunity.service.crud;

import org.filrouge.gymcommunity.model.entity.BaseEntity;
import org.filrouge.gymcommunity.service.support.FindAndExecuteService;

public interface CreateByIdService<
        RES,
        REQ,
        T extends BaseEntity<ID>,
        ID
        > extends FindAndExecuteService<RES, REQ, T, ID> {

    default RES createById(ID id, REQ dto) {
        return findAndExecute(id, entity -> {
            getMapper().fromRequestDTO(dto);;
            T updatedEntity = getRepository().save(entity);
            return getMapper().toResponseDTO(updatedEntity);
        });
    }
}

