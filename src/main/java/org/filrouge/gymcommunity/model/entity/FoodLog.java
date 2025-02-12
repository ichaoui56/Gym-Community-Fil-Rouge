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
public class FoodLog extends BaseEntity<Integer> {
    private float quantity;
    private float calories;

    @ManyToOne
    private User user;

    @ManyToOne
    private Food food;
}

