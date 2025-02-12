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
public class Forum extends BaseEntity<Integer> {
    private String title;
    private String description;

    @ManyToOne
    private Admin createdBy;

    @OneToMany(mappedBy = "forum")
    private List<Discussion> discussions;
}

