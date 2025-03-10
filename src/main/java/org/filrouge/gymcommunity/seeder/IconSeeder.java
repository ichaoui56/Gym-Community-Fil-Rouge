package org.filrouge.gymcommunity.seeder;

import org.filrouge.gymcommunity.model.entity.Icon;
import org.filrouge.gymcommunity.repository.IconRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class IconSeeder {

    @Bean
    public CommandLineRunner seedIcons(IconRepository iconRepository) {
        return args -> {
            List<Icon> icons = List.of(
                    new Icon("Fitness", "#FF4500", "fitness_center"),
                    new Icon("Running", "#0079D3", "directions_run"),
                    new Icon("Cycling", "#00A368", "directions_bike"),
                    new Icon("Swimming", "#7E3FF2", "pool"),
                    new Icon("Weightlifting", "#FF4500", "fitness_center"),
                    new Icon("Yoga", "#FF8717", "self_improvement"),
                    new Icon("Boxing", "#FF66AC", "sports_mma"),
                    new Icon("Basketball", "#0079D3", "sports_basketball"),
                    new Icon("Soccer", "#00A368", "sports_soccer"),
                    new Icon("Tennis", "#7E3FF2", "sports_tennis"),
                    new Icon("Golf", "#FF4500", "sports_golf"),
                    new Icon("Volleyball", "#0079D3", "sports_volleyball"),
                    new Icon("Hiking", "#00A368", "hiking"),
                    new Icon("Meditation", "#7E3FF2", "spa"),
                    new Icon("Nutrition", "#FF4500", "restaurant"),
                    new Icon("Supplements", "#0079D3", "medication"),
                    new Icon("Stretching", "#00A368", "accessibility_new"),
                    new Icon("Crossfit", "#7E3FF2", "timer"),
                    new Icon("Bodybuilding", "#FF4500", "sports_kabaddi"),
                    new Icon("Cardio", "#0079D3", "monitor_heart"),
                    new Icon("Pilates", "#00A368", "airline_seat_flat"),
                    new Icon("Martial Arts", "#7E3FF2", "sports_martial_arts"),
                    new Icon("Personal Training", "#FF4500", "person"),
                    new Icon("Group Fitness", "#0079D3", "groups")
            );

            // Avoid duplicates
            icons.forEach(icon -> {
                if (!iconRepository.existsByName(icon.getName())) {
                    iconRepository.save(icon);
                }
            });
        };
    }
}