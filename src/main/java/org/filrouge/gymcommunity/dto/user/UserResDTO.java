package org.filrouge.gymcommunity.dto.user;

import org.filrouge.gymcommunity.model.ActivityLevel;
import org.filrouge.gymcommunity.model.Goal;

public record UserResDTO(
        Integer id,
        String name,
        String email,
        float height,
        float weight,
        ActivityLevel activityLevel,
        Goal goal,
        float dailyCalorieGoal,
        float caloriesLeft
) {}

