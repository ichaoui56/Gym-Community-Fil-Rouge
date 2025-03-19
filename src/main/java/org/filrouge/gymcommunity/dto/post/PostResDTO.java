package org.filrouge.gymcommunity.dto.post;

import org.filrouge.gymcommunity.dto.comment.CommentResDTO;
import org.filrouge.gymcommunity.dto.forum.ForumEmbdResDTO;
import org.filrouge.gymcommunity.dto.user.UserResDTO;

import java.time.LocalDateTime;
import java.util.List;

public record PostResDTO(
        Integer id,
        String title,
        String content,
        LocalDateTime createAt,
        int voteCount,
        ForumEmbdResDTO forum,
        List<CommentResDTO> comments,
        UserResDTO user
) {
}
