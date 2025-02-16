package org.filrouge.gymcommunity.dto.forum;

public record ForumReqDTO(
        String title,
        String description,
        String category,
        String icon
) {}
