package org.filrouge.gymcommunity.helper.calculeNutrition;

public class CarbsFatRatio {
    private final double carbsPercentage;
    private final double fatsPercentage;

    public CarbsFatRatio(double carbs, double fats) {
        this.carbsPercentage = carbs;
        this.fatsPercentage = fats;
    }

    public double getCarbsPercentage() {
        return carbsPercentage;
    }

    public double getFatsPercentage() {
        return fatsPercentage;
    }
}
