package org.filrouge.gymcommunity.service.support;

import org.filrouge.gymcommunity.model.entity.BaseEntity;
import org.filrouge.gymcommunity.service.base.BaseService;
import java.util.UUID;
import java.util.function.Function;

public interface ExecuteWithIdService<RES, REQ, T extends BaseEntity<ID>, ID> extends BaseService<RES, REQ, T, ID> {

    default <R> R executeWithUUID(String id, Function<UUID, R> function) {
        try {
            UUID uuid = UUID.fromString(id);
            return function.apply(uuid);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format: " + id, e);
        }
    }
}
