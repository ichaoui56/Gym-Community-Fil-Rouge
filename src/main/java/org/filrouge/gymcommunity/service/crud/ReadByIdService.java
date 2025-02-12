package org.filrouge.gymcommunity.service.crud;

import org.filrouge.gymcommunity.service.support.FindEntityByIdService;
import org.filrouge.gymcommunity.model.entity.BaseEntity;

public interface ReadByIdService<
        RES,
        REQ,
        T extends BaseEntity<ID>, // Ensure T is a JPA entity
        ID
        > extends FindEntityByIdService<RES, REQ, T, ID> {

    default RES readById(ID id) {
        T entity = findEntityById(id);
        return getMapper().toResponseDTO(entity);
    }
}
