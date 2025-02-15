package org.filrouge.gymcommunity.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

@Entity
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment extends BaseEntity<Integer> {
    private String text;

    @ManyToOne
    private Blog blog;

    @ManyToOne
    private AppUser author;

    private int likes;

}
