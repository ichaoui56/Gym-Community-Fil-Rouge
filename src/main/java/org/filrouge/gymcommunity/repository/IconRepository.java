package org.filrouge.gymcommunity.repository;

import org.filrouge.gymcommunity.model.entity.Icon;
import org.springframework.stereotype.Repository;

@Repository
public interface IconRepository extends GenericRepository<Icon, Integer>{
    boolean existsByName(String name);
}
