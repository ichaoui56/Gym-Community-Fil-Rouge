package org.filrouge.gymcommunity.repository;

import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends GenericRepository<Post,Integer>{
    Page<Post> findByUser_Id(Integer userId, Pageable pageable);
}
