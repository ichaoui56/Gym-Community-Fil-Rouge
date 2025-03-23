package org.filrouge.gymcommunity.service.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.filrouge.gymcommunity.service.base.BaseService;
import org.filrouge.gymcommunity.model.entity.BaseEntity;
import org.springframework.data.domain.Sort;

public interface ReadAllService<
        RES,
        REQ,
        T extends BaseEntity<ID>, // Ensure T is a JPA entity
        ID>
        extends BaseService<RES, REQ, T, ID> {

    default Page<RES> readAll(Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "createdAt")
        );
        Page<T> entities = getRepository().findAll(sortedPageable);
        return entities.map(getMapper()::toResponseDTO);
    }

}
