package org.filrouge.gymcommunity.controller;

import org.filrouge.gymcommunity.dto.vote.VoteReqDTO;
import org.filrouge.gymcommunity.dto.vote.VoteResDTO;
import org.filrouge.gymcommunity.helper.SecurityHelper;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.Vote;
import org.filrouge.gymcommunity.service.services.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote")
public class VoteController extends GenericController<VoteResDTO, VoteReqDTO, Vote, Integer>{

    private final VoteService voteService;
    private final SecurityHelper securityHelper;

    public VoteController(VoteService voteService, SecurityHelper securityHelper){
        super(voteService, Vote.class);
        this.voteService = voteService;
        this.securityHelper = securityHelper;
    }

    @PostMapping
    public ResponseEntity<VoteResDTO> handleVote(@RequestBody VoteReqDTO voteReqDTO) {
        AppUser user = securityHelper.getAuthenticatedUser();
        return ResponseEntity.ok(voteService.handleVote(voteReqDTO, user));
    }
}
