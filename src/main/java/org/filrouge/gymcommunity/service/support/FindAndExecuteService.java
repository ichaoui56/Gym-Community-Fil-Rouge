package org.filrouge.gymcommunity.service.support;

import org.filrouge.gymcommunity.model.entity.BaseEntity;
import java.util.function.Function;

public interface FindAndExecuteService<RES, REQ, T extends BaseEntity<ID>, ID>
        extends FindEntityByIdService<RES, REQ, T, ID> {

    default <R> R findAndExecute(ID id, Function<T, R> function) {
        T entity = findEntityById(id);
        return function.apply(entity);
    }
}
