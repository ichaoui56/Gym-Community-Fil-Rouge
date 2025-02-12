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

    @ManyToOne
    private Forum forum;

    @ManyToOne
    private User user;
}

