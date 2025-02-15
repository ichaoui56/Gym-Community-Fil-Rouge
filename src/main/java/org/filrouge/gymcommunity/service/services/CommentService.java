package org.filrouge.gymcommunity.service.services;

import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.dto.comment.CommentReqDTO;
import org.filrouge.gymcommunity.dto.comment.CommentResDTO;
import org.filrouge.gymcommunity.mapper.CommentMapper;
import org.filrouge.gymcommunity.mapper.GenericMapper;
import org.filrouge.gymcommunity.model.entity.Comment;
import org.filrouge.gymcommunity.repository.CommentRepository;
import org.filrouge.gymcommunity.repository.GenericRepository;
import org.filrouge.gymcommunity.service.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService extends GenericServiceImpl<CommentResDTO, CommentReqDTO, Comment, Integer> {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public GenericRepository<Comment, Integer> getRepository() {
        return commentRepository;
    }

    @Override
    public GenericMapper<Comment, CommentResDTO, CommentReqDTO> getMapper() {
        return commentMapper;
    }
}
