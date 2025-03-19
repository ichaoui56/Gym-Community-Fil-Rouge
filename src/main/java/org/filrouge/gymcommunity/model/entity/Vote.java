package org.filrouge.gymcommunity.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.filrouge.gymcommunity.model.VoteType;

@Entity
@Table(name = "post_vote",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "post_id"}))
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vote extends BaseEntity<Integer> {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VoteType voteType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}