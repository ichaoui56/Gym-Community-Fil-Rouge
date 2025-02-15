package org.filrouge.gymcommunity.dto.blog;

import org.filrouge.gymcommunity.dto.comment.CommentResDTO;
import org.filrouge.gymcommunity.dto.user.UserResDTO;
import org.filrouge.gymcommunity.model.entity.AppUser;

import java.time.LocalDateTime;
import java.util.List;

public record BlogResDTO(
        Integer id,
        String title,
        String content,
        String category,
        List<CommentResDTO> comments,
        int likes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UserResDTO author
) {}
