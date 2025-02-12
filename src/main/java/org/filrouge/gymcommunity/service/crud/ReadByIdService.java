package org.filrouge.gymcommunity.service.crud;


import org.filrouge.gymcommunity.service.support.FindEntityByIdService;

public interface ReadByIdService<
        RES,
        REQ,
        T,
        ID
        > extends FindEntityByIdService<RES, REQ, T, ID> {

    default RES readById(ID id) {
        T entity = findEntityById(id);
        return getMapper().toResponseDTO(entity);
    }
}
