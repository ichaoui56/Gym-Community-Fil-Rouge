package org.filrouge.gymcommunity.controller;

import org.filrouge.gymcommunity.dto.forum.ForumReqDTO;
import org.filrouge.gymcommunity.dto.forum.ForumResDTO;
import org.filrouge.gymcommunity.model.entity.Forum;
import org.filrouge.gymcommunity.service.services.ForumService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("forum")
public class ForumController extends GenericController<ForumResDTO, ForumReqDTO, Forum, Integer>{
    public ForumController(ForumService forumService){
        super(forumService, Forum.class);
    }
}
