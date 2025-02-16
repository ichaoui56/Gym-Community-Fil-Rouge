package org.filrouge.gymcommunity.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.filrouge.gymcommunity.dto.blog.BlogReqDTO;
import org.filrouge.gymcommunity.dto.forum.ForumReqDTO;
import org.filrouge.gymcommunity.model.entity.Admin;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.Blog;
import org.filrouge.gymcommunity.model.entity.Forum;
import org.filrouge.gymcommunity.repository.AdminRepository;
import org.filrouge.gymcommunity.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ForumAspect {

    private final AdminRepository adminRepository;


    /**
     * Sets the author of a Blog entity before persisting it.
     */
    @Around("execution(* org.filrouge.gymcommunity.mapper.ForumMapper.fromRequestDTO(..)) && args(forumReqDTO)")
    public Object setForumAuthor(ProceedingJoinPoint joinPoint, ForumReqDTO forumReqDTO) throws Throwable {
        Forum forum = (Forum) joinPoint.proceed();

        if (forum.getId() == null) {
            Admin user = getAuthenticatedUser();
            forum.setCreatedBy(user);
        }

        return forum;
    }

    /**
     * Retrieves the authenticated user from the security context.
     *
     * @return The authenticated AppUser.
     * @throws UsernameNotFoundException if the authenticated user cannot be found.
     */
    private Admin getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Authenticated user with email " + email + " not found"));
    }
}
