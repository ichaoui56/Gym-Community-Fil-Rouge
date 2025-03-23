package org.filrouge.gymcommunity.service.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.dto.post.PostResDTO;
import org.filrouge.gymcommunity.dto.vote.VoteReqDTO;
import org.filrouge.gymcommunity.dto.vote.VoteResDTO;
import org.filrouge.gymcommunity.mapper.GenericMapper;
import org.filrouge.gymcommunity.mapper.VoteMapper;
import org.filrouge.gymcommunity.model.VoteType;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.Post;
import org.filrouge.gymcommunity.model.entity.Vote;
import org.filrouge.gymcommunity.repository.GenericRepository;
import org.filrouge.gymcommunity.repository.PostRepository;
import org.filrouge.gymcommunity.repository.VoteRepository;
import org.filrouge.gymcommunity.service.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteService extends GenericServiceImpl<VoteResDTO, VoteReqDTO, Vote, Integer> {

    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;
    private final PostRepository postRepository;

    @Override
    public GenericRepository<Vote, Integer> getRepository() {
        return voteRepository;
    }

    @Override
    public GenericMapper<Vote, VoteResDTO, VoteReqDTO> getMapper() {
        return voteMapper;
    }

    /**
     * Handles voting logic with proper toggling behavior
     * - If no vote exists: create new vote with requested type
     * - If vote exists with same type: remove the vote (toggle off)
     * - If vote exists with different type: update vote type
     */
    @Transactional
    public VoteResDTO handleVote(VoteReqDTO voteReqDTO, AppUser user) {
        Post post = postRepository.findById(voteReqDTO.postId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        Optional<Vote> existingVote = voteRepository.findByPostAndUser(post, user);
        boolean removed = false;
        Vote vote = null;

        // Track the vote change delta (e.g., +1 for UP, -1 for DOWN)
        int delta = 0;

        if (existingVote.isPresent()) {
            Vote existing = existingVote.get();
            if (existing.getVoteType() == voteReqDTO.voteType()) {
                // Toggle off: Remove the vote
                voteRepository.delete(existing);
                delta = (existing.getVoteType() == VoteType.UP) ? -1 : 1; // Reverse the effect
                removed = true;
            } else {
                // Change vote type (e.g., UP → DOWN)
                delta = (voteReqDTO.voteType() == VoteType.UP) ? 2 : -2; // UP to DOWN: -2, DOWN to UP: +2
                existing.setVoteType(voteReqDTO.voteType());
                vote = voteRepository.save(existing);
            }
        } else {
            // New vote
            delta = (voteReqDTO.voteType() == VoteType.UP) ? 1 : -1;
            vote = voteRepository.save(Vote.builder()
                    .post(post)
                    .user(user)
                    .voteType(voteReqDTO.voteType())
                    .build());
        }
        // Update the post's vote count
        post.setVoteCount(post.getVoteCount() + delta);
        postRepository.save(post); // Save the updated vote count

        // Include voteColor in the VoteResDTO constructor
        return new VoteResDTO(
                vote != null ? vote.getId() : -1, // Use -1 or another default value if vote is null
                post.getId(),
                user.getId(),
                removed ? null : voteReqDTO.voteType(),
                post.getVoteCount()
        );
    }
}