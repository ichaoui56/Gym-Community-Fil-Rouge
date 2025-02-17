package org.filrouge.gymcommunity.dto.discussion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DiscussionReqDTO(
        @NotBlank(message = "Message is required")
        @Size(min = 1, max = 500, message = "Comment text must be between 1 and 500 characters")
        String message
) {
}
