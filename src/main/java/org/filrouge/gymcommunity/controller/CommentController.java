package org.filrouge.gymcommunity.controller;

import org.filrouge.gymcommunity.dto.comment.CommentReqDTO;
import org.filrouge.gymcommunity.dto.comment.CommentResDTO;
import org.filrouge.gymcommunity.model.entity.Comment;
import org.filrouge.gymcommunity.service.services.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController extends GenericController<CommentResDTO, CommentReqDTO, Comment, Integer>{
    public CommentController(CommentService commentService){
        super(commentService, Comment.class);
    }
}
