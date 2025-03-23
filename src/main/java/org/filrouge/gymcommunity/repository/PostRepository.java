package org.filrouge.gymcommunity.repository;

import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends GenericRepository<Post, Integer> {
    Page<Post> findByUser_Id(Integer userId, Pageable pageable);
    Page<Post> findByForum_Id(Integer forumId, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE NOT EXISTS (SELECT v FROM Vote v WHERE v.post = p AND v.user = :user)")
    List<Post> findUnvotedPostsByUser(AppUser user);

    @Query("SELECT p FROM Post p WHERE p.id = :id")
    Optional<Post> findByIdWithRefreshedVoteCount(@Param("id") Integer id);
}