package org.filrouge.gymcommunity.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.filrouge.gymcommunity.dto.forum.ForumReqDTO;
import org.filrouge.gymcommunity.helper.SecurityHelper;
import org.filrouge.gymcommunity.model.entity.Admin;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.Forum;
import org.filrouge.gymcommunity.model.entity.Icon;
import org.filrouge.gymcommunity.repository.IconRepository;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ForumAspect {

    private final SecurityHelper securityHelper;
    private final IconRepository iconRepository;

    /**
     * Sets the author of a Blog entity before persisting it.
     */
    @Around("execution(* org.filrouge.gymcommunity.mapper.ForumMapper.fromRequestDTO(..)) && args(forumReqDTO)")
    public Object setForumAuthor(ProceedingJoinPoint joinPoint, ForumReqDTO forumReqDTO) throws Throwable {
        Forum forum = (Forum) joinPoint.proceed();

        Icon icon = iconRepository.findById(forumReqDTO.iconId())
                .orElseThrow(() -> new RuntimeException("Icon not found with ID: " + forumReqDTO.iconId()));

        if (forum.getId() == null) {
            AppUser user = securityHelper.getAuthenticatedUser();
            forum.setCreatedBy(user);
            forum.setIcon(icon);
        }

        return forum;
    }
}
