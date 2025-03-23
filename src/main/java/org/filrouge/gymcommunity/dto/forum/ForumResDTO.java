package org.filrouge.gymcommunity.dto.forum;

import org.filrouge.gymcommunity.dto.admin.AdminResDTO;
import org.filrouge.gymcommunity.dto.icon.IconResDTO;
import org.filrouge.gymcommunity.dto.post.PostResDTO;
import org.filrouge.gymcommunity.dto.user.UserResDTO;
import org.filrouge.gymcommunity.model.entity.Icon;

import java.time.LocalDateTime;
import java.util.List;

public record ForumResDTO(
        Integer id,
        String title,
        String description,
        IconResDTO icon,
        List<PostResDTO> posts,
        List<UserResDTO> members,
        LocalDateTime createdAt,
        boolean isMember
) {
}
