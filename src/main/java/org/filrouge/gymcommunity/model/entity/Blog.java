package org.filrouge.gymcommunity.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Entity
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Blog extends BaseEntity<Integer> {
    private String title;
    private String content;

    @ManyToOne
    private AppUser author;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments;
}
