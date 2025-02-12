package org.filrouge.gymcommunity.service;

import org.filrouge.gymcommunity.model.entity.BaseEntity;
import org.filrouge.gymcommunity.service.crud.*;

public interface CrudService<RES, REQ, T extends BaseEntity<ID>, ID> extends
        CreateService<RES, REQ, T, ID>,
        ReadByIdService<RES, REQ, T, ID>,
        ReadAllService<RES, REQ, T, ID>,
        UpdateService<RES, REQ, T, ID>,
        DeleteService<RES, REQ, T, ID> {
}
