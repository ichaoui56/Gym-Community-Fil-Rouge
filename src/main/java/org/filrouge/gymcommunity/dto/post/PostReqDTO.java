package org.filrouge.gymcommunity.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostReqDTO(
        @NotBlank(message = "Category is required")
        @Size(min = 3, max = 100, message = "Title text must be between 3 and 100 characters")
        String title,

        @NotBlank(message = "Category is required")
        String category,

        String content
) {
}
