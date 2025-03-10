package org.filrouge.gymcommunity.service.services;

import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.dto.post.PostReqDTO;
import org.filrouge.gymcommunity.dto.post.PostResDTO;
import org.filrouge.gymcommunity.mapper.PostMapper;
import org.filrouge.gymcommunity.mapper.GenericMapper;
import org.filrouge.gymcommunity.model.entity.Post;
import org.filrouge.gymcommunity.repository.GenericRepository;
import org.filrouge.gymcommunity.repository.PostRepository;
import org.filrouge.gymcommunity.service.GenericServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService extends GenericServiceImpl<PostResDTO, PostReqDTO, Post, Integer> {

    private final PostMapper postMapper;
    private final PostRepository postRepository;

    @Override
    public GenericRepository<Post, Integer> getRepository() {
        return postRepository;
    }

    @Override
    public GenericMapper<Post, PostResDTO, PostReqDTO> getMapper() {
        return postMapper;
    }

    public Page<PostResDTO> getPostsByUserId(Integer userId, Pageable pageable) {
        Page<Post> posts = postRepository.findByUser_Id(userId, pageable);
        return posts.map(postMapper::toResponseDTO);
    }
}
