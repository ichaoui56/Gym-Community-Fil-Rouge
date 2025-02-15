package org.filrouge.gymcommunity.dto.userNutr;

public record UserNutritionResDTO(
        float dailyCalorieGoal,
        float proteins,
        float carbs,
        float fats
) {}