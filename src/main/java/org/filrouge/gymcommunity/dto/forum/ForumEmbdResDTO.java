package org.filrouge.gymcommunity.dto.forum;

import org.filrouge.gymcommunity.dto.icon.IconResDTO;
import org.filrouge.gymcommunity.dto.user.UserResDTO;

import java.time.LocalDateTime;
import java.util.List;

public record ForumEmbdResDTO(
        Integer id,
        String title,
        String description,
        IconResDTO icon,
        List<UserResDTO> members,
        LocalDateTime createdAt,
        boolean isMember
) {
}
