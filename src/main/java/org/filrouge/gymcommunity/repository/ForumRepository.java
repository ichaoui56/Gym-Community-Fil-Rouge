package org.filrouge.gymcommunity.repository;

import org.filrouge.gymcommunity.model.entity.Forum;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumRepository extends GenericRepository<Forum, Integer>{
    Forum findForumById(int id);
    List<Forum> findByMembers_Id(Integer userId);
    List<Forum> findTop3ByOrderByCreatedAtDesc();
}
