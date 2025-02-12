package org.filrouge.gymcommunity.controller;

import org.filrouge.gymcommunity.service.CrudService;
import org.filrouge.gymcommunity.model.entity.BaseEntity;

public interface BaseController<RES, REQ, T extends BaseEntity<ID>, ID> {
    CrudService<RES, REQ, T, ID> getService();

    Class<T> getEntityClass();
}
