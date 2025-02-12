package org.filrouge.gymcommunity.service;

import org.filrouge.gymcommunity.model.entity.BaseEntity;

public abstract class GenericServiceImpl<RES, REQ, T extends BaseEntity<ID>, ID> implements CrudService<RES, REQ, T, ID> {
}
