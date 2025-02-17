package org.filrouge.gymcommunity.helper.calculeNutrition;

import lombok.Getter;

@Getter
public class CalorieCalculationResult {
    private final long dailyCalories;
    private final long proteins;
    private final long carbs;
    private final long fats;

    public CalorieCalculationResult(long dailyCalories, long proteins, long carbs, long fats) {
        this.dailyCalories = dailyCalories;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
    }
}
