package org.filrouge.gymcommunity.dto.forum;

import org.filrouge.gymcommunity.dto.admin.AdminResDTO;
import org.filrouge.gymcommunity.model.entity.Admin;
import org.filrouge.gymcommunity.model.entity.AppUser;

import java.util.List;

public record ForumResDTO(
        Integer id,
        String title,
        String description,
        String category,
        String icon,
        AdminResDTO createdBy
//        List<DiscussionDto> discussions
) {}
