package org.filrouge.gymcommunity.mapper;

import org.filrouge.gymcommunity.dto.discussion.DiscussionReqDTO;
import org.filrouge.gymcommunity.dto.discussion.DiscussionResDTO;
import org.filrouge.gymcommunity.model.entity.Discussion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscussionMapper extends GenericMapper<Discussion, DiscussionResDTO, DiscussionReqDTO>{
}
