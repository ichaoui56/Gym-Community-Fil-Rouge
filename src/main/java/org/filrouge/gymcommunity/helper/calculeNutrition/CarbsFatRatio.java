package org.filrouge.gymcommunity.helper.calculeNutrition;

import lombok.Getter;

@Getter
public class CarbsFatRatio {
    private final double carbsPercentage;
    private final double fatsPercentage;

    public CarbsFatRatio(double carbs, double fats) {
        this.carbsPercentage = carbs;
        this.fatsPercentage = fats;
    }

}
