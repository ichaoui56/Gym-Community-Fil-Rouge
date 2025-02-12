package org.filrouge.gymcommunity.model.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Progress extends BaseEntity<Integer> {
    private float weight;
    private float bodyFatPercentage;
    private float muscleMass;
    private String comments;

    @ManyToOne
    private User user;
}

