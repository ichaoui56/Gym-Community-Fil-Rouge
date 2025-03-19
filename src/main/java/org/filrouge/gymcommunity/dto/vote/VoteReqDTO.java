package org.filrouge.gymcommunity.dto.vote;

import org.filrouge.gymcommunity.model.VoteType;

public record VoteReqDTO(
        int postId,
        VoteType voteType
) {}