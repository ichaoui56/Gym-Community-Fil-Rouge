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
public class Discussion extends BaseEntity<Integer> {
    private String message;
    private int likes;

    @ManyToOne
    private Forum forum;

    @ManyToOne
    private AppUser user;
}

