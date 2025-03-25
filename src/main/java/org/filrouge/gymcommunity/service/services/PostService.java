package org.filrouge.gymcommunity.service.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.dto.post.PostReqDTO;
import org.filrouge.gymcommunity.dto.post.PostResDTO;
import org.filrouge.gymcommunity.mapper.PostMapper;
import org.filrouge.gymcommunity.mapper.GenericMapper;
import org.filrouge.gymcommunity.model.VoteType;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.Post;
import org.filrouge.gymcommunity.model.entity.Vote;
import org.filrouge.gymcommunity.repository.GenericRepository;
import org.filrouge.gymcommunity.repository.PostRepository;
import org.filrouge.gymcommunity.repository.VoteRepository;
import org.filrouge.gymcommunity.service.GenericServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService extends GenericServiceImpl<PostResDTO, PostReqDTO, Post, Integer> {

    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final VoteRepository voteRepository;

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

    public Page<PostResDTO> getPostsByForumId(Integer forumId, Pageable pageable) {
        Page<Post> posts = postRepository.findByForum_Id(forumId, pageable);
        return posts.map(postMapper::toResponseDTO);
    }

    public List<PostResDTO> getVotedPostsByUser(AppUser user) {
        return voteRepository.findByUser(user).stream()
                .filter(vote -> vote.getVoteType() == VoteType.UP)
                .map(vote -> postMapper.toResponseDTO(vote.getPost()))
                .collect(Collectors.toList());
    }

    public List<PostResDTO> getUnvotedPostsByUser(AppUser user) {
        return voteRepository.findByUser(user).stream()
                .filter(vote -> vote.getVoteType() == VoteType.DOWN)
                .map(vote -> postMapper.toResponseDTO(vote.getPost()))
                .collect(Collectors.toList());
    }

}
