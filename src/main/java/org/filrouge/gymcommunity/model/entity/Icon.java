package org.filrouge.gymcommunity.model.entity;


import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Icon extends BaseEntity<Integer>{

    private String name;

    private String color;

    private String icon;
}
