package org.filrouge.gymcommunity.controller;

import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.dto.SimpleSuccessDTO;
import org.filrouge.gymcommunity.dto.post.PostReqDTO;
import org.filrouge.gymcommunity.dto.post.PostResDTO;
import org.filrouge.gymcommunity.model.entity.Post;
import org.filrouge.gymcommunity.service.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.filrouge.gymcommunity.response.Response.simpleSuccess;

@RestController
@RequestMapping("/post")
public class PostController extends GenericController<PostResDTO, PostReqDTO, Post, Integer>{
    private final PostService postService;

    public PostController(PostService postService){
        super(postService, Post.class);
        this.postService = postService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<SimpleSuccessDTO> getPostsByUser(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PostResDTO> posts = postService.getPostsByUserId(userId, pageable);
        return simpleSuccess(200, "Posts retrieved successfully", posts);
    }
}
