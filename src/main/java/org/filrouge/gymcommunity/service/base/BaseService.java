package org.filrouge.gymcommunity.service.base;

import org.filrouge.gymcommunity.mapper.GenericMapper;
import org.filrouge.gymcommunity.repository.GenericRepository;

public interface BaseService<
        RES,
        REQ,
        T,
        ID> {
    GenericRepository<T, ID> getRepository();
    GenericMapper<T, RES, REQ> getMapper();
}
