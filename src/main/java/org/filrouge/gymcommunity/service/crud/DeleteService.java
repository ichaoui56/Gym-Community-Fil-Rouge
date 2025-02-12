package org.filrouge.gymcommunity.service.crud;

import org.filrouge.gymcommunity.model.entity.BaseEntity;
import org.filrouge.gymcommunity.service.support.FindAndExecuteService;

public interface DeleteService<
        RES,
        REQ,
        T extends BaseEntity<ID>,
        ID>
        extends FindAndExecuteService<RES, REQ, T, ID> {

    default void delete(ID id) {
        findAndExecute(id, entity -> {
            getRepository().delete(entity);
            return null;
        });
    }
}
