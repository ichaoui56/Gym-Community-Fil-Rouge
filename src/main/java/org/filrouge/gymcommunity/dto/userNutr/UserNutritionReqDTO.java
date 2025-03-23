package org.filrouge.gymcommunity.dto.userNutr;

import org.filrouge.gymcommunity.model.*;

import java.time.LocalDate;

public record UserNutritionReqDTO(
        ActivityLevel activityLevel,
        int age,
        DietStyle dietStyle,
        Goal goal,
        float height,
        String heightUnit,
        Gender sex,
        LocalDate targetDate,
        float targetWeight,
        float weight,
        String weightUnit,
        WorkoutLevel workoutLevel,
        double proteinPercentage,
        double carbPercentage,
        double fatPercentage
) {
    public UserNutritionReqDTO {
        if (proteinPercentage + carbPercentage + fatPercentage != 100) {
            throw new IllegalArgumentException("Protein, carb, and fat percentages must sum to 100");
        }
    }
}