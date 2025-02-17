package org.filrouge.gymcommunity.controller;

import org.filrouge.gymcommunity.dto.discussion.DiscussionReqDTO;
import org.filrouge.gymcommunity.dto.discussion.DiscussionResDTO;
import org.filrouge.gymcommunity.model.entity.Discussion;
import org.filrouge.gymcommunity.service.services.DiscussionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discussion")
public class DiscussionController extends GenericController<DiscussionResDTO, DiscussionReqDTO, Discussion, Integer>{
    public DiscussionController(DiscussionService discussionService){
        super(discussionService, Discussion.class);
    }
}
