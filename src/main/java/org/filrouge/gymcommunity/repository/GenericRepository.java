package org.filrouge.gymcommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericRepository<ENT, ID> extends JpaRepository<ENT, ID> {
}
