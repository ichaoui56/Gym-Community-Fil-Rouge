package org.filrouge.gymcommunity.seeder;

import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.Forum;
import org.filrouge.gymcommunity.model.entity.Icon;
import org.filrouge.gymcommunity.repository.UserRepository;
import org.filrouge.gymcommunity.repository.ForumRepository;
import org.filrouge.gymcommunity.repository.IconRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class ForumSeeder {

    @Bean
    public CommandLineRunner seedForums(ForumRepository forumRepository, UserRepository appUserRepository, IconRepository iconRepository) {
        return args -> {
            if (forumRepository.count() > 0) {
                return; // Avoid duplicate data insertion
            }

            AppUser user = appUserRepository.findById(1000)
                    .orElseThrow(() -> new RuntimeException("User with ID 102 not found"));

            List<Forum> forums = List.of(
                    createForum("FitnessTraining", "A place to discuss workout routines, strength training, and fitness tips.", 352, user, iconRepository, 0),
                    createForum("RunningCommunity", "Join fellow runners to talk about training, marathons, and the best running gear.", 353, user, iconRepository, 5),
                    createForum("CyclingHub", "Everything about cycling, from road biking to mountain trails. Discuss gear, routes, and techniques.", 354, user, iconRepository, 10),
                    createForum("SwimmingWorld", "A space for swimmers to share techniques, workouts, and experiences.", 355, user, iconRepository, 15),
                    createForum("WeightLiftingClub", "Strength training, powerlifting, and bodybuilding discussions.", 356, user, iconRepository, 20),
                    createForum("YogaWellness", "Explore yoga practices, mindfulness, and flexibility with like-minded people.", 357, user, iconRepository, 25),
                    createForum("BoxingCorner", "Discuss boxing techniques, training drills, and equipment.", 358, user, iconRepository, 30),
                    createForum("BasketballTalk", "Everything basketball—from NBA discussions to local games.", 359, user, iconRepository, 35),
                    createForum("SoccerNation", "A hub for soccer enthusiasts to chat about matches, players, and training.", 360, user, iconRepository, 40),
                    createForum("TennisForum", "Talk about the latest tennis tournaments, equipment, and techniques.", 361, user, iconRepository, 45),
                    createForum("GolfClub", "A place to discuss golf swings, courses, and tournaments.", 362, user, iconRepository, 50),
                    createForum("VolleyballZone", "Join discussions on volleyball techniques, games, and training.", 363, user, iconRepository, 55),
                    createForum("HikingAdventures", "Share hiking experiences, best trails, and gear recommendations.", 364, user, iconRepository, 60),
                    createForum("MeditationSpace", "Explore different meditation techniques, stress relief tips, and mindfulness.", 365, user, iconRepository, 65),
                    createForum("NutritionForum", "Discuss diets, meal plans, and healthy eating tips.", 366, user, iconRepository, 70),
                    createForum("SupplementsTalk", "A forum dedicated to vitamins, proteins, and supplements.", 367, user, iconRepository, 75),
                    createForum("StretchingZone", "Discover the best stretching exercises for flexibility and recovery.", 368, user, iconRepository, 80),
                    createForum("CrossfitCommunity", "A place for CrossFit enthusiasts to share WODs, tips, and success stories.", 369, user, iconRepository, 85),
                    createForum("BodybuildingForum", "From muscle-building techniques to diet plans, connect with bodybuilders.", 370, user, iconRepository, 90),
                    createForum("CardioTraining", "Discuss the best cardio workouts for endurance and fat loss.", 371, user, iconRepository, 95),
                    createForum("PilatesCommunity", "Connect with other Pilates enthusiasts to share techniques and tips.", 372, user, iconRepository, 100),
                    createForum("MartialArtsZone", "Discuss various martial arts, training drills, and techniques.", 373, user, iconRepository, 105),
                    createForum("PersonalTraining", "A space for personal trainers and trainees to share knowledge and tips.", 374, user, iconRepository, 110),
                    createForum("GroupFitness", "Talk about group fitness classes, training schedules, and best practices.", 375, user, iconRepository, 115),
                    createForum("StrengthAndConditioning", "Focus on building strength, conditioning, and athletic performance.", 352, user, iconRepository, 120),
                    createForum("CrossFitTraining", "Learn about CrossFit, WODs, and workout challenges.", 353, user, iconRepository, 125),
                    createForum("ZumbaClass", "Join discussions on Zumba classes and fun dance workouts.", 354, user, iconRepository, 130),
                    createForum("CyclingClub", "Connect with cyclists to discuss gear, routes, and performance.", 355, user, iconRepository, 135),
                    createForum("RunningGear", "Talk about the latest in running shoes, gadgets, and accessories.", 356, user, iconRepository, 140),
                    createForum("PowerliftingForum", "Share techniques, tips, and progress in powerlifting.", 357, user, iconRepository, 145),
                    createForum("IndoorFitness", "Explore home workouts, gym routines, and fitness equipment.", 358, user, iconRepository, 150),
                    createForum("NutritionForAthletes", "Discuss sports nutrition, supplements, and diet plans for athletes.", 359, user, iconRepository, 155),
                    createForum("SportsRecovery", "Learn about post-workout recovery strategies and muscle rehabilitation.", 360, user, iconRepository, 160),
                    createForum("HealthyLiving", "Share tips on living a healthy lifestyle, from diet to mental well-being.", 361, user, iconRepository, 165),
                    createForum("MartialArtsTraining", "For martial arts enthusiasts to learn and share training techniques.", 362, user, iconRepository, 170),
                    createForum("BodyweightWorkouts", "Share bodyweight exercises and home fitness routines.", 363, user, iconRepository, 175),
                    createForum("StretchingTechniques", "Discuss effective stretching methods to improve flexibility and mobility.", 364, user, iconRepository, 180),
                    createForum("YogaTherapy", "Explore therapeutic yoga techniques for health and wellness.", 365, user, iconRepository, 185),
                    createForum("FitnessApps", "Review fitness apps, track progress, and share recommendations.", 366, user, iconRepository, 190),
                    createForum("WeightLossJourney", "Share weight loss stories, challenges, and motivational tips.", 367, user, iconRepository, 195),
                    createForum("PostWorkoutNutrition", "Discuss the best meals and supplements after workouts for muscle recovery.", 368, user, iconRepository, 200),
                    createForum("GroupWorkouts", "Organize and share group workout sessions, from outdoor runs to gym classes.", 369, user, iconRepository, 205),
                    createForum("HIITTraining", "Join discussions on High-Intensity Interval Training and fat burning exercises.", 370, user, iconRepository, 260),
                    createForum("OutdoorFitness", "Share tips for outdoor workouts, parks, and outdoor running events.", 371, user, iconRepository, 265),
                    createForum("SportsPerformance", "Talk about improving sports performance in various activities.", 322, user, iconRepository, 270),
                    createForum("GymMotivation", "Discuss fitness motivation, overcoming plateaus, and staying consistent.", 373, user, iconRepository, 275),
                    createForum("StretchingForFlexibility", "Share routines and tips to improve flexibility and mobility.", 374, user, iconRepository, 230),
                    createForum("SportsInjuryPrevention", "Talk about preventing injuries during workouts, sports, and physical activities.", 375, user, iconRepository, 235)
            );

            forumRepository.saveAll(forums);
        };
    }

    private Forum createForum(String title, String description, int iconId, AppUser createdBy, IconRepository iconRepository, int secondsOffset) {
        Icon icon = iconRepository.findById(iconId).orElse(null);
        return Forum.builder()
                .title(title)
                .description(description)
                .icon(icon)
                .createdBy(createdBy)
                .createdAt(LocalDateTime.now().plusSeconds(secondsOffset))
                .build();
    }
}