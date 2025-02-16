package org.filrouge.gymcommunity.repository;

import org.filrouge.gymcommunity.model.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends GenericRepository<Admin, Integer>{
    Optional<Admin> findByEmail(String email);
}
