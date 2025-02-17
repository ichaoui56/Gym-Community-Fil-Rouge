package org.filrouge.gymcommunity.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.filrouge.gymcommunity.dto.forum.ForumReqDTO;
import org.filrouge.gymcommunity.helper.SecurityHelper;
import org.filrouge.gymcommunity.model.entity.Admin;
import org.filrouge.gymcommunity.model.entity.Forum;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ForumAspect {

    private final SecurityHelper securityHelper;

    /**
     * Sets the author of a Blog entity before persisting it.
     */
    @Around("execution(* org.filrouge.gymcommunity.mapper.ForumMapper.fromRequestDTO(..)) && args(forumReqDTO)")
    public Object setForumAuthor(ProceedingJoinPoint joinPoint, ForumReqDTO forumReqDTO) throws Throwable {
        Forum forum = (Forum) joinPoint.proceed();

        if (forum.getId() == null) {
            Admin user = securityHelper.getAuthenticatedAdmin();
            forum.setCreatedBy(user);
        }

        return forum;
    }
}
