package org.filrouge.gymcommunity.dto.user;

import jakarta.validation.constraints.*;
import org.filrouge.gymcommunity.marker.validation.OnCreate;
import org.filrouge.gymcommunity.marker.validation.OnUpdate;
import org.filrouge.gymcommunity.model.ActivityLevel;
import org.filrouge.gymcommunity.model.Goal;

public record UserReqDTO(
        @NotBlank(message = "Name is required", groups = {OnCreate.class, OnUpdate.class})
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters", groups = {OnCreate.class, OnUpdate.class})
        String name,

        @NotBlank(message = "Email is required", groups = {OnCreate.class})
        @Email(message = "Invalid email format", groups = {OnCreate.class})
        String email,

        @NotBlank(message = "Password is required", groups = {OnCreate.class})
        @Size(min = 8, message = "Password must be at least 8 characters", groups = {OnCreate.class})
        String password,

        float height,

        float weight,

        ActivityLevel activityLevel,

        Goal goal
) {
}
