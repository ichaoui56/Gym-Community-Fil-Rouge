package org.filrouge.gymcommunity.service.support;

import java.util.function.Function;

public interface FindAndExecuteService<RES, REQ, T, ID> extends FindEntityByIdService<RES, REQ, T, ID> {

    default <R> R findAndExecute(ID id, Function<T, R> function) {
        T entity = findEntityById(id);
        return function.apply(entity);
    }
}
