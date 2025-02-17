package org.filrouge.gymcommunity.dto.forum;

import org.filrouge.gymcommunity.dto.admin.AdminResDTO;
import org.filrouge.gymcommunity.dto.discussion.DiscussionResDTO;

import java.util.List;

public record ForumEmbdResDTO(
        Integer id,
        String title,
        String description,
        String category,
        String icon,
        AdminResDTO createdBy
) {
}
