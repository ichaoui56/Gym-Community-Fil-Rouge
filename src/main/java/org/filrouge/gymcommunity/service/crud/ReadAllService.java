package org.filrouge.gymcommunity.service.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.filrouge.gymcommunity.service.base.BaseService;

public interface ReadAllService<
        RES,
        REQ,
        T,
        ID>
        extends BaseService<RES, REQ, T, ID> {

    default Page<RES> readAll(Pageable pageable) {
        Page<T> entities = getRepository().findAll(pageable);
        return entities.map(getMapper()::toResponseDTO);
    }
}
