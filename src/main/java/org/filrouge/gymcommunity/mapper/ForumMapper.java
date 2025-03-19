package org.filrouge.gymcommunity.mapper;

import org.filrouge.gymcommunity.dto.forum.ForumReqDTO;
import org.filrouge.gymcommunity.dto.forum.ForumResDTO;
import org.filrouge.gymcommunity.model.entity.Forum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ForumMapper extends GenericMapper<Forum, ForumResDTO, ForumReqDTO>{

    @Override
    @Mapping(source = "member", target = "isMember")
    ForumResDTO toResponseDTO(Forum forum);
}
