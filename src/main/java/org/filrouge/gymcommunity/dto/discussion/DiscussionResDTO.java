package org.filrouge.gymcommunity.dto.discussion;

import org.filrouge.gymcommunity.dto.forum.ForumEmbdResDTO;
import org.filrouge.gymcommunity.dto.user.UserResDTO;

public record DiscussionResDTO(
        Integer id,
        String message,
        UserResDTO user,
        ForumEmbdResDTO forum
) {
}
