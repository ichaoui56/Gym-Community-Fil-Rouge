package org.filrouge.gymcommunity.controller;

import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.dto.SimpleSuccessDTO;
import org.filrouge.gymcommunity.dto.forum.ForumReqDTO;
import org.filrouge.gymcommunity.dto.forum.ForumResDTO;
import org.filrouge.gymcommunity.model.entity.Forum;
import org.filrouge.gymcommunity.service.services.ForumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.filrouge.gymcommunity.response.Response.simpleSuccess;

@RestController
@RequestMapping("forum")
public class ForumController extends GenericController<ForumResDTO, ForumReqDTO, Forum, Integer>{

    private final ForumService forumService;

    public ForumController(ForumService forumService){
        super(forumService, Forum.class);
        this.forumService = forumService;
    }

    @PostMapping("/{forumId}/join/{userId}")
    public ResponseEntity<?> joinForum(@PathVariable Integer forumId, @PathVariable Integer userId) {
        ForumResDTO updatedForum = forumService.joinForum(forumId, userId);
        System.out.println("forumRes " + updatedForum);
        return ResponseEntity.ok(updatedForum);
    }

    @PostMapping("/{forumId}/leave/{userId}")
    public ResponseEntity<?> leaveForum(@PathVariable Integer forumId, @PathVariable Integer userId) {
        ForumResDTO updatedForum = forumService.leaveForum(forumId, userId);
        return ResponseEntity.ok(updatedForum);
    }
}
