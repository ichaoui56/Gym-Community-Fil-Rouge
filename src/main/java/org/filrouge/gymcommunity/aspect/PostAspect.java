package org.filrouge.gymcommunity.aspect;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.filrouge.gymcommunity.dto.comment.CommentReqDTO;
import org.filrouge.gymcommunity.dto.forum.ForumReqDTO;
import org.filrouge.gymcommunity.dto.post.PostReqDTO;
import org.filrouge.gymcommunity.helper.SecurityHelper;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.Comment;
import org.filrouge.gymcommunity.model.entity.Forum;
import org.filrouge.gymcommunity.model.entity.Post;
import org.filrouge.gymcommunity.repository.ForumRepository;
import org.filrouge.gymcommunity.repository.PostRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class PostAspect {

    private final ForumRepository forumRepository;
    private final SecurityHelper securityHelper;
    private final PostRepository postRepository;

    @Around("execution(* org.filrouge.gymcommunity.mapper.PostMapper.fromRequestDTO(..)) && args(postReqDTO)")
    public Object setForumId(ProceedingJoinPoint joinPoint, PostReqDTO postReqDTO) throws Throwable {
        Post post = (Post) joinPoint.proceed();

        Forum forum = forumRepository.findById(postReqDTO.forumId())
                        .orElseThrow(()-> new UsernameNotFoundException("Forum Not Found with ID: " + postReqDTO.forumId()));
        System.out.println("forumId from objet: "+ forum.getId());
        System.out.println("forumId from dto: "+ postReqDTO.forumId());
        if (post.getId() == null) {
            AppUser user = securityHelper.getAuthenticatedUser();
            post.setUser(user);
            post.setForum(forum);
            return post;
        }
        return joinPoint.proceed();
    }

    /**
     * Sets the author of a Blog entity before persisting it.
     */
    @Around("execution(* org.filrouge.gymcommunity.mapper.CommentMapper.fromRequestDTO(..)) && args(commentReq)")
    public Object setCommentUser(ProceedingJoinPoint joinPoint, CommentReqDTO commentReq) throws Throwable {
        Comment comment = (Comment) joinPoint.proceed();

        if (comment.getId() == null) {
            AppUser user = securityHelper.getAuthenticatedUser();
            Post blog = postRepository.findById(commentReq.postId())
                    .orElseThrow(() -> new UsernameNotFoundException("Post with ID " + commentReq.postId() + " not found"));

            comment.setAuthor(user);
            comment.setPost(blog);
        }

        return comment;
    }
}
