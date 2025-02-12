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
public class Food extends BaseEntity<Integer> {
    private String name;
    private float caloriesPerGram;
    private float proteinPerGram;
    private float carbsPerGram;
    private float fatsPerGram;
}
