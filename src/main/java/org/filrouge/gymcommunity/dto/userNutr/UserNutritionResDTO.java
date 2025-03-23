package org.filrouge.gymcommunity.dto.userNutr;

public record UserNutritionResDTO(
        long dailyCalorieGoal,
        long proteins,
        long carbs,
        long fats
) {}