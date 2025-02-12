package org.filrouge.gymcommunity.service.support;

import jakarta.persistence.EntityNotFoundException;
import org.filrouge.gymcommunity.service.base.BaseService;


/**
 * Service interface for finding an entity by its ID.
 *
 * @param <RES> Response DTO type
 * @param <REQ> Request DTO type
 * @param <T>   Entity type
 */
public interface FindEntityByIdService<RES, REQ, T, ID> extends BaseService<RES, REQ, T, ID> {
    /**
     * Finds an entity by its ID.
     *
     * @param id the ID of the entity
     * @return the entity
     * @throws EntityNotFoundException if the entity is not found
     */
    default T findEntityById(ID id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }
}

