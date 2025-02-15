package org.filrouge.gymcommunity.repository;

import org.filrouge.gymcommunity.model.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends GenericRepository<Comment, Integer> {
}
