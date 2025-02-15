package org.filrouge.gymcommunity.mapper;

import org.filrouge.gymcommunity.dto.comment.CommentResDTO;
import org.filrouge.gymcommunity.dto.comment.CommentReqDTO;
import org.filrouge.gymcommunity.model.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper extends GenericMapper<Comment, CommentResDTO, CommentReqDTO> {
}
