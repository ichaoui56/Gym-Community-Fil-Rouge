package org.filrouge.gymcommunity.service.base;

import org.filrouge.gymcommunity.mapper.GenericMapper;
import org.filrouge.gymcommunity.model.entity.BaseEntity;
import org.filrouge.gymcommunity.repository.GenericRepository;

public interface BaseService<RES, REQ, T extends BaseEntity<ID>, ID> {
    GenericRepository<T, ID> getRepository();
    GenericMapper<T, RES, REQ> getMapper();
}
