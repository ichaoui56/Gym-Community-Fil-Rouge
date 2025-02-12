package org.filrouge.gymcommunity.service.support;

import org.filrouge.gymcommunity.service.base.BaseService;

import java.util.UUID;
import java.util.function.Function;

public interface ExecuteWithIdService<RES, REQ, T, ID> extends BaseService<RES, REQ, T, ID> {
    default <R> R executeWithUUID(String id, Function<UUID, R> function) {
        UUID uuid = UUID.fromString(id);
        return function.apply(uuid);
    }
}
