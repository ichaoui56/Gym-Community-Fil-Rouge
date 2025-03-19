package org.filrouge.gymcommunity.repository;

import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.Post;
import org.filrouge.gymcommunity.model.entity.Vote;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends GenericRepository<Vote,Integer>{
    Optional<Vote> findByPostAndUser(Post post, AppUser user);
}
