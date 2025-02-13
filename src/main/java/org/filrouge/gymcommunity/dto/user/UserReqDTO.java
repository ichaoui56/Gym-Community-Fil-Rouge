package org.filrouge.gymcommunity.dto.user;

import jakarta.validation.constraints.*;

import org.filrouge.gymcommunity.model.ActivityLevel;
import org.filrouge.gymcommunity.model.Goal;

public record UserReqDTO(
        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password,

        @Min(value = 50, message = "Height must be at least 50 cm")
        @Max(value = 250, message = "Height must be at most 250 cm")
        float height,

        @Min(value = 20, message = "Weight must be at least 20 kg")
        @Max(value = 300, message = "Weight must be at most 300 kg")
        float weight,

        @NotNull(message = "Activity level is required")
        ActivityLevel activityLevel,

        @NotNull(message = "Goal is required")
        Goal goal
) {}