package org.filrouge.gymcommunity.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.filrouge.gymcommunity.dto.blog.BlogReqDTO;
import org.filrouge.gymcommunity.dto.comment.CommentReqDTO;
import org.filrouge.gymcommunity.dto.user.UserReqDTO;
import org.filrouge.gymcommunity.exception.UserAlreadyExistsException;
import org.filrouge.gymcommunity.exception.UserPhoneAlreadyExistsException;
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

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    @Around("execution(* org.filrouge.gymcommunity.mapper.BlogMapper.fromRequestDTO(..)) && args(blogReqDTO)")
    public Object setBlogAuthor(ProceedingJoinPoint joinPoint, BlogReqDTO blogReqDTO) throws Throwable {
        Blog blog = (Blog) joinPoint.proceed();

        if (blog.getId() == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            AppUser user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            blog.setAuthor(user);
        }

        return blog;
    }

    @Around("execution(* org.filrouge.gymcommunity.mapper.CommentMapper.fromRequestDTO(..)) && args(commentReq)")
    public Object setCommentUser(ProceedingJoinPoint joinPoint, CommentReqDTO commentReq) throws Throwable {
        Comment comment = (Comment) joinPoint.proceed();

        if (comment.getId() == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            AppUser user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            Blog blog = blogRepository.findById(commentReq.blogId())
                            .orElseThrow(()->new UsernameNotFoundException("blog not found"));
            comment.setAuthor(user);
            comment.setBlog(blog);
        }

        return comment;
    }
}
