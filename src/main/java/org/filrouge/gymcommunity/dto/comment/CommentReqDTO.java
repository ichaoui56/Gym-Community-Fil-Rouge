package org.filrouge.gymcommunity.dto.comment;

import jakarta.validation.constraints.*;

public record CommentReqDTO(
        @NotBlank(message = "Text is required")
        @Size(min = 1, max = 500, message = "Comment text must be between 1 and 500 characters")
        String text,

        @NotNull(message = "Blog ID is required")
        Integer postId
) {}
