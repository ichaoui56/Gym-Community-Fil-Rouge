package org.filrouge.gymcommunity.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.filrouge.gymcommunity.dto.blog.BlogReqDTO;
import org.filrouge.gymcommunity.dto.comment.CommentReqDTO;
import org.filrouge.gymcommunity.helper.SecurityHelper;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.Blog;
import org.filrouge.gymcommunity.model.entity.Comment;
import org.filrouge.gymcommunity.repository.BlogRepository;
import org.filrouge.gymcommunity.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class BlogAspect {

    private final BlogRepository blogRepository;
    private final SecurityHelper securityHelper;

    /**
     * Sets the author of a Blog entity before persisting it.
     */
    @Around("execution(* org.filrouge.gymcommunity.mapper.BlogMapper.fromRequestDTO(..)) && args(blogReqDTO)")
    public Object setBlogAuthor(ProceedingJoinPoint joinPoint, BlogReqDTO blogReqDTO) throws Throwable {
        Blog blog = (Blog) joinPoint.proceed();

        if (blog.getId() == null) {
            AppUser user = securityHelper.getAuthenticatedUser();
            blog.setAuthor(user);
        }

        return blog;
    }

    /**
     * Sets the author and blog reference for a Comment entity before persisting it.
     */
    @Around("execution(* org.filrouge.gymcommunity.mapper.CommentMapper.fromRequestDTO(..)) && args(commentReq)")
    public Object setCommentUser(ProceedingJoinPoint joinPoint, CommentReqDTO commentReq) throws Throwable {
        Comment comment = (Comment) joinPoint.proceed();

        if (comment.getId() == null) {
            AppUser user = securityHelper.getAuthenticatedUser();
            Blog blog = blogRepository.findById(commentReq.blogId())
                    .orElseThrow(() -> new UsernameNotFoundException("Blog with ID " + commentReq.blogId() + " not found"));

            comment.setAuthor(user);
            comment.setBlog(blog);
        }

        return comment;
    }

    /**
     * Checks if a blog exists before updating and ensures the update process proceeds.
     */
    @Around(value = "execution(* org.filrouge.gymcommunity.service.crud.UpdateService.update(..)) && args(id, dto)", argNames = "joinPoint,id,dto")
    public Object checkBlogExistenceAndSetApproved(ProceedingJoinPoint joinPoint, Integer id, BlogReqDTO dto) throws Throwable {
        blogRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Blog not found with id: " + id));

        Object result = joinPoint.proceed();

        return result;
    }

    /**
     * Ensures the blog's approved status is updated based on the provided BlogReqDTO.
     *
     * @param joinPoint The join point representing the intercepted method.
     * @param blogReq The request DTO containing the updated blog information.
     * @param blog The blog entity being updated.
     * @return The updated blog entity with the approved status set.
     * @throws Throwable if an error occurs during execution.
     */
    @Around(value = "execution(* org.filrouge.gymcommunity.mapper.BlogMapper.updateEntity(..)) && args(blogReq, blog)", argNames = "joinPoint,blogReq,blog")
    public Object setBlogApproved(ProceedingJoinPoint joinPoint, BlogReqDTO blogReq, Blog blog) throws Throwable {
        joinPoint.proceed();
        blog.setApproved(blogReq.isApproved());

        return blog;
    }
}
