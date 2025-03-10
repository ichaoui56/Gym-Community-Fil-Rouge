package org.filrouge.gymcommunity.dto.post;

import jakarta.validation.constraints.NotBlank;
import org.filrouge.gymcommunity.dto.forum.ForumEmbdResDTO;
import org.filrouge.gymcommunity.dto.user.UserResDTO;

public record PostResDTO(
        Integer id,
        String title,
        String category,
        String content,
        UserResDTO user
) {
}
