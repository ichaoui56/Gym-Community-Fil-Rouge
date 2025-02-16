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
public class Admin extends BaseEntity<Integer> {
    private String email;
    private String password;
    private String role;

    @OneToMany
    private List<Blog> approvedBlogs;

    @OneToMany(mappedBy = "createdBy")
    private List<Forum> createdForums;
}


