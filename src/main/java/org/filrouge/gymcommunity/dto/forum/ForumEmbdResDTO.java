package org.filrouge.gymcommunity.dto.forum;

import org.filrouge.gymcommunity.dto.admin.AdminResDTO;

public record ForumEmbdResDTO(
        Integer id,
        String title,
        String description,
        String category,
        String icon,
        AdminResDTO createdBy
) {
}
