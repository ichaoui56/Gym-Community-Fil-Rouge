package org.filrouge.gymcommunity.dto.forum;

import org.filrouge.gymcommunity.dto.icon.IconResDTO;

public record ForumReqDTO(
        String title,
        String description,
        int iconId
) {}
