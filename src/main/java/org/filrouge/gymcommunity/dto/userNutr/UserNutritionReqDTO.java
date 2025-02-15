package org.filrouge.gymcommunity.dto.userNutr;

import org.filrouge.gymcommunity.model.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record UserNutritionReqDTO(
        @NotNull(message = "Gender is required")
        Gender gender,

        @NotNull(message = "Weight is required")
        @Min(value = 1, message = "Weight must be greater than 0")
        float weight,

        @NotNull(message = "Height is required")
        @Min(value = 1, message = "Height must be greater than 0")
        float height,

        @NotNull(message = "Age is required")
        @Min(value = 1, message = "Age must be greater than 0")
        int age,

        @NotNull(message = "Activity level is required")
        ActivityLevel activityLevel,

        @NotNull(message = "Target date is required")
        @FutureOrPresent(message = "Target date must be in the future or present")
        LocalDate targetDate,

        @NotNull(message = "Target weight is required")
        @Min(value = 1, message = "Target weight must be greater than 0")
        float targetWeight,

        @NotNull(message = "Goal is required")
        Goal goal,

        @NotNull(message = "Workout level is required")
        WorkoutLevel workoutLevel,

        @NotNull(message = "Eating style is required")
        EatingStyle eatingStyle
) {}
