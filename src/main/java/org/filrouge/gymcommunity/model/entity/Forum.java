package org.filrouge.gymcommunity.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Entity
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Forum extends BaseEntity<Integer> {

    @Column(unique = true)
    private String title;

    private String description;

    @ManyToOne
    private Icon icon;

    @ManyToOne
    private AppUser createdBy;

    @ManyToMany
    @JoinTable(
            name = "forum_members",
            joinColumns = @JoinColumn(name = "forum_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<AppUser> members = new HashSet<>();

    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @Transient
    private boolean isMember;

    public boolean isMember(AppUser user) {
        return members.contains(user);
    }

    @Override
    public String toString() {
        return "Forum{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", icon=" + icon +
                ", createdBy=" + createdBy +
                ", isMember=" + isMember +
                '}';
    }
}