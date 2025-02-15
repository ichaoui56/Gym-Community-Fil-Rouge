package org.filrouge.gymcommunity.dto.blog;

import jakarta.validation.constraints.*;
import java.util.List;

public record BlogReqDTO(
        @NotBlank(message = "Title is required")
        @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
        String title,

        @NotBlank(message = "Content is required")
        String content,

        @NotBlank(message = "Category is required")
        String category

) {}
