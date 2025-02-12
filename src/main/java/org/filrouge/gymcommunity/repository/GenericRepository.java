package org.filrouge.gymcommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.filrouge.gymcommunity.model.entity.BaseEntity;

public interface GenericRepository<T extends BaseEntity<ID>, ID> extends JpaRepository<T, ID> {
}
