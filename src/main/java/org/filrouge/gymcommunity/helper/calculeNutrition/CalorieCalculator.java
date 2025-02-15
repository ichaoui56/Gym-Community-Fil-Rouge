package org.filrouge.gymcommunity.helper.calculeNutrition;

import org.filrouge.gymcommunity.dto.userNutr.UserNutritionResDTO;
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
            EatingStyle eatingStyle
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
        if (goal == Goal.MAINTAIN_WEIGHT) {
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

        // Step 4: Calculate protein based on workout level
        double proteinPerKg = getProteinPerKg(workoutLevel);
        double proteinGrams = weight * proteinPerKg;
        double proteinCalories = proteinGrams * 4;

        // Ensure protein calories do not exceed total calories
        double remainingCalories;
        if (proteinCalories > goalCalories) {
            proteinCalories = goalCalories;
            proteinGrams = proteinCalories / 4;
            remainingCalories = 0;
        } else {
            remainingCalories = goalCalories - proteinCalories;
        }

        // Step 5: Split remaining into carbs and fats based on eating style
        CarbsFatRatio ratio = getCarbsFatRatio(eatingStyle);
        double carbsCalories = remainingCalories * ratio.getCarbsPercentage();
        double fatsCalories = remainingCalories * ratio.getFatsPercentage();

        // Convert to grams
        double carbsGrams = carbsCalories / 4;
        double fatsGrams = fatsCalories / 9;

        return new CalorieCalculationResult(
                Math.round(goalCalories),
                Math.round(proteinGrams),
                Math.round(carbsGrams),
                Math.round(fatsGrams)
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
        switch (activityLevel) {
            case SEDENTARY:
                return 1.2;
            case LIGHTLY_ACTIVE:
                return 1.375;
            case MODERATELY_ACTIVE:
                return 1.55;
            case VERY_ACTIVE:
                return 1.725;
            case SUPER_ACTIVE:
                return 1.9;
            default:
                return 1.2;
        }
    }

    private static double getProteinPerKg(WorkoutLevel workoutLevel) {
        switch (workoutLevel) {
            case NONE:
                return 1.2;
            case LIGHT:
                return 1.5;
            case MODERATE:
                return 1.8;
            case INTENSE:
                return 2.2;
            case PROFESSIONAL:
            case ELITE:
                return 2.5;
            default:
                return 1.2;
        }
    }

    private static CarbsFatRatio getCarbsFatRatio(EatingStyle eatingStyle) {
        switch (eatingStyle) {
            case KETO:
                return new CarbsFatRatio(0.05, 0.95);
            case PALEO:
                return new CarbsFatRatio(0.25, 0.75);
            case CARNIVORE:
                return new CarbsFatRatio(0.1, 0.9);
            case VEGAN:
                return new CarbsFatRatio(0.6, 0.4);
            case VEGETARIAN:
                return new CarbsFatRatio(0.5, 0.5);
            case MEDITERRANEAN:
                return new CarbsFatRatio(0.5, 0.5);
            case OMNIVORE:
                return new CarbsFatRatio(0.4, 0.6);
            case PEDESTRIAN:
                return new CarbsFatRatio(0.4, 0.6);
            default:
                return new CarbsFatRatio(0.4, 0.6);
        }
    }
}
