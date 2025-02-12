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
public class User extends BaseEntity<Integer> {
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

    @OneToMany(mappedBy = "user")
    private List<FoodLog> foodLogs;

    @OneToMany(mappedBy = "user")
    private List<Progress> progressRecords;

    @OneToMany(mappedBy = "author")
    private List<Blog> blogs;

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;
}

