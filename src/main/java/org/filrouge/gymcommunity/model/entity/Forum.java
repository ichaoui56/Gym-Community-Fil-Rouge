package org.filrouge.gymcommunity.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.filrouge.gymcommunity.model.ForumStatus;

import java.util.*;

@Entity
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Forum extends BaseEntity<Integer> {

    @Column(unique = true)
    private String title;

    private String description;

    @ManyToOne
    private Icon icon;

    @Enumerated(EnumType.STRING)
    private ForumStatus status = ForumStatus.PENDING;

    @ManyToOne
    private AppUser createdBy;

    @ManyToOne
    private Admin approvedBy;

    @ManyToMany
    @JoinTable(
            name = "forum_members",
            joinColumns = @JoinColumn(name = "forum_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<AppUser> members = new HashSet<>();

    @OneToMany(mappedBy = "forum")
    private List<Post> posts;

}

