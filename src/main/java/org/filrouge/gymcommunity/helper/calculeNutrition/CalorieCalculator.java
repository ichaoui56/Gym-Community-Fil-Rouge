package org.filrouge.gymcommunity.helper.calculeNutrition;

import org.filrouge.gymcommunity.model.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalorieCalculator {

    public static final int CALORIES_PER_KG = 7700;

    public static CalorieCalculationResult calculate(
            Gender gender,
            float weight,
            float height,
            int age,
            ActivityLevel activityLevel,
            LocalDate targetDate,
            float targetWeight,
            Goal goal,
            WorkoutLevel workoutLevel,
            DietStyle eatingStyle,
            double proteinPercentage, // Add protein percentage
            double carbPercentage,    // Add carb percentage
            double fatPercentage      // Add fat percentage
    ) {
        // Step 1: Calculate BMR
        double bmr = calculateBMR(gender, weight, height, age);

        // Step 2: Apply activity level multiplier
        double activityFactor = getActivityFactor(activityLevel);
        double maintenanceCalories = bmr * activityFactor;

        // Step 3: Adjust for goal based on target weight and date
        LocalDate currentDate = LocalDate.now();
        long daysUntilTarget = ChronoUnit.DAYS.between(currentDate, targetDate);
        if (daysUntilTarget <= 0) {
            daysUntilTarget = 1; // Avoid division by zero or negative days
        }
        float weightDifference = targetWeight - weight;
        double calorieAdjustment = (weightDifference * CALORIES_PER_KG) / daysUntilTarget;

        double goalCalories;
        if (goal == Goal.RECOMP) {
            goalCalories = maintenanceCalories;
        } else {
            goalCalories = maintenanceCalories + calorieAdjustment;
        }

        // Ensure minimum calorie intake
        if (gender == Gender.FEMALE && goalCalories < 1200) {
            goalCalories = 1200;
        } else if (gender == Gender.MALE && goalCalories < 1500) {
            goalCalories = 1500;
        }

        // Step 4: Calculate protein, carbs, and fats based on percentages
        double proteinCalories = (proteinPercentage / 100) * goalCalories;
        double carbCalories = (carbPercentage / 100) * goalCalories;
        double fatCalories = (fatPercentage / 100) * goalCalories;

        // Convert calories to grams
        double proteinGrams = proteinCalories / 4;
        double carbGrams = carbCalories / 4;
        double fatGrams = fatCalories / 9;

        return new CalorieCalculationResult(
                Math.round(goalCalories),
                Math.round(proteinGrams),
                Math.round(carbGrams),
                Math.round(fatGrams)
        );
    }

    private static double calculateBMR(Gender gender, float weight, float height, int age) {
        if (gender == Gender.MALE) {
            return 10 * weight + 6.25 * height - 5 * age + 5;
        } else {
            return 10 * weight + 6.25 * height - 5 * age - 161;
        }
    }

    private static double getActivityFactor(ActivityLevel activityLevel) {
        return switch (activityLevel) {
            case VERY_LIGHT -> 1.2;
            case LIGHT -> 1.375;
            case MODERATE -> 1.55;
            case HIGH -> 1.725;
        };
    }

    private static double getProteinPerKg(WorkoutLevel workoutLevel) {
        return switch (workoutLevel) {
            case VERY_LIGHT -> 1.2;
            case LIGHT -> 1.5;
            case MODERATE -> 1.8;
            case INTENSE -> 2.2;
            case VERY_INTENSE -> 2.5;
        };
    }
}