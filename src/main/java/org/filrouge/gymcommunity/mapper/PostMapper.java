package org.filrouge.gymcommunity.mapper;

import org.filrouge.gymcommunity.dto.post.PostReqDTO;
import org.filrouge.gymcommunity.dto.post.PostResDTO;
import org.filrouge.gymcommunity.model.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper extends GenericMapper<Post, PostResDTO, PostReqDTO>{
}
