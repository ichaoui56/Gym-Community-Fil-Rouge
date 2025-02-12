package org.filrouge.gymcommunity.controller;

import org.filrouge.gymcommunity.service.CrudService;

public interface BaseController<RES, REQ, T, ID> {
    CrudService<RES, REQ, T, ID> getService();

    Class<T> getEntityClass();

}
