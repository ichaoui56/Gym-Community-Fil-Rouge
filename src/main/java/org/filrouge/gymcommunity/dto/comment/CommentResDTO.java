package org.filrouge.gymcommunity.dto.comment;

import org.filrouge.gymcommunity.dto.user.UserResDTO;

import java.time.LocalDateTime;

public record CommentResDTO(
        Integer id,
        String text,
        UserResDTO author,
        int postId,
        LocalDateTime createdAt
) {}
