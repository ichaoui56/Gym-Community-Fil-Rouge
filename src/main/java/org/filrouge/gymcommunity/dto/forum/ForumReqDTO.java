package org.filrouge.gymcommunity.dto.forum;

public record ForumReqDTO(
        String title,
        String description,
        int iconId,
        boolean isMember
) {}
