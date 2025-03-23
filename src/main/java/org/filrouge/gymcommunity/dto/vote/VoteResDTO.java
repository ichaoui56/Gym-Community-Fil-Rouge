package org.filrouge.gymcommunity.dto.vote;

import org.filrouge.gymcommunity.model.VoteType;

public record VoteResDTO(
        int id,
        int postId,
        int userId, VoteType voteType,
        int voteCount
) {}