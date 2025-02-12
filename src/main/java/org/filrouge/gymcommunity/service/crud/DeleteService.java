package org.filrouge.gymcommunity.service.crud;


import org.filrouge.gymcommunity.service.support.FindAndExecuteService;

public interface DeleteService<
        RES,
        REQ,
        T,
        ID>
        extends FindAndExecuteService<RES, REQ, T, ID> {

    default void delete(ID id) {
        findAndExecute(id, entity -> {
            getRepository().delete(entity);
            return null;
        });
    }
}
