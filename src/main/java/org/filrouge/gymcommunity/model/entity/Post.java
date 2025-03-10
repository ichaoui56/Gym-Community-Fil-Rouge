package org.filrouge.gymcommunity.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.BaseEntity;
import org.filrouge.gymcommunity.model.entity.Forum;

import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post extends BaseEntity<Integer> {
    private String title;

    @Column(length = 1000)
    private String content;
    private int likes;
    private String category;

    @ManyToOne
    private Forum forum;

    @ManyToOne
    private AppUser user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}

