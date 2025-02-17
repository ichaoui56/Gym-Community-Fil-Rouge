package org.filrouge.gymcommunity.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ForumIdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String forumId = request.getHeader("Forum-Id");

        if (forumId != null) {
            HttpSession session = request.getSession();
            session.setAttribute("forum-id", forumId);
        }
        System.out.println(forumId);
        return true;
    }
}