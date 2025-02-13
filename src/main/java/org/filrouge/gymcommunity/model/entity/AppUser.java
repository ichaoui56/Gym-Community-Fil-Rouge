package org.filrouge.gymcommunity.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.filrouge.gymcommunity.model.ActivityLevel;
import org.filrouge.gymcommunity.model.Goal;

import java.util.List;

@Entity
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "\"user\"")
public class AppUser extends BaseEntity<Integer> {
    private String name;
    private String email;
    private String password;
    private float height;
    private float weight;

    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;

    @Enumerated(EnumType.STRING)
    private Goal goal;

    private float dailyCalorieGoal;
    private float caloriesLeft;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<FoodLog> foodLogs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Progress> progressRecords;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Blog> blogs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Notification> notifications;
}
