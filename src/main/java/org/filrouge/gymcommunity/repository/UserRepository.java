package org.filrouge.gymcommunity.repository;

import org.filrouge.gymcommunity.model.entity.AppUser;
import java.util.Optional;

public interface UserRepository extends GenericRepository<AppUser, Integer> {
    Optional<AppUser> findByEmail(String email);
    boolean existsByEmail(String email);

}
