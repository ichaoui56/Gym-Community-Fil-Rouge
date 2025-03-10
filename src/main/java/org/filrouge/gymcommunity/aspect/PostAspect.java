package org.filrouge.gymcommunity.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.filrouge.gymcommunity.helper.SecurityHelper;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.Post;
import org.filrouge.gymcommunity.repository.ForumRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
@RequiredArgsConstructor
public class PostAspect {

    private final ForumRepository forumRepository;
    private final SecurityHelper securityHelper;

    @Around("execution(* org.filrouge.gymcommunity.mapper.PostMapper.fromRequestDTO(..))")
    public Object setForumId(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String forumId = (String) request.getSession().getAttribute("forum-id");

        if (forumId != null) {
            Post discussion = (Post) joinPoint.proceed();
            AppUser user = securityHelper.getAuthenticatedUser();
            discussion.setForum(forumRepository.findById(Integer.parseInt(forumId))
                    .orElseThrow(()-> new UsernameNotFoundException("Forum Not Found with ID: " + forumId)));
            discussion.setUser(user);

            return discussion;
        }
        return joinPoint.proceed();
    }

    /**
     * Sets the author of a Blog entity before persisting it.
     */
    @Around("execution(* org.filrouge.gymcommunity.mapper.PostMapper.fromRequestDTO(..))")
    public Object setPostAuthor(ProceedingJoinPoint joinPoint) throws Throwable {
        Post post = (Post) joinPoint.proceed();

        if (post.getId() == null) {
            AppUser user = securityHelper.getAuthenticatedUser();
            post.setUser(user);
        }

        return post;
    }
}
