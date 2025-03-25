package org.filrouge.gymcommunity.service;

import org.filrouge.gymcommunity.dto.vote.VoteReqDTO;
import org.filrouge.gymcommunity.dto.vote.VoteResDTO;
import org.filrouge.gymcommunity.mapper.VoteMapper;
import org.filrouge.gymcommunity.model.VoteType;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.Post;
import org.filrouge.gymcommunity.model.entity.Vote;
import org.filrouge.gymcommunity.repository.PostRepository;
import org.filrouge.gymcommunity.repository.VoteRepository;
import org.filrouge.gymcommunity.service.services.VoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VoteServiceTest {

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private VoteMapper voteMapper;

    @InjectMocks
    private VoteService voteService;

    private AppUser testUser;
    private Post testPost;
    private VoteReqDTO testVoteReqDTO;
    private Vote existingUpVote;
    private Vote existingDownVote;

    @BeforeEach
    void setUp() {
        testUser = AppUser.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .build();

        testPost = Post.builder()
                .id(1)
                .title("Test Post")
                .content("Test Content")
                .voteCount(5)
                .build();

        testVoteReqDTO = new VoteReqDTO(1, VoteType.UP);

        existingUpVote = Vote.builder()
                .id(1)
                .post(testPost)
                .user(testUser)
                .voteType(VoteType.UP)
                .build();

        existingDownVote = Vote.builder()
                .id(2)
                .post(testPost)
                .user(testUser)
                .voteType(VoteType.DOWN)
                .build();
    }


    @Test
    void handleVote_NewUpVote_ShouldCreateVoteAndIncrementCount() {
        when(postRepository.findById(1)).thenReturn(Optional.of(testPost));
        when(voteRepository.findByPostAndUser(testPost, testUser)).thenReturn(Optional.empty());
        when(voteRepository.save(any(Vote.class))).thenReturn(existingUpVote);

        VoteResDTO result = voteService.handleVote(testVoteReqDTO, testUser);

        assertEquals(VoteType.UP, result.voteType());
        assertEquals(6, result.voteCount());
        verify(voteRepository).save(any(Vote.class));
        verify(postRepository).save(testPost);
    }

    @Test
    void handleVote_NewDownVote_ShouldCreateVoteAndDecrementCount() {
        VoteReqDTO downVoteReq = new VoteReqDTO(1, VoteType.DOWN);
        when(postRepository.findById(1)).thenReturn(Optional.of(testPost));
        when(voteRepository.findByPostAndUser(testPost, testUser)).thenReturn(Optional.empty());
        when(voteRepository.save(any(Vote.class))).thenReturn(existingDownVote);

        VoteResDTO result = voteService.handleVote(downVoteReq, testUser);

        assertEquals(VoteType.DOWN, result.voteType());
        assertEquals(4, result.voteCount());
        verify(voteRepository).save(any(Vote.class));
        verify(postRepository).save(testPost);
    }

    @Test
    void handleVote_ExistingSameVote_ShouldRemoveVoteAndReverseEffect() {
        when(postRepository.findById(1)).thenReturn(Optional.of(testPost));
        when(voteRepository.findByPostAndUser(testPost, testUser)).thenReturn(Optional.of(existingUpVote));

        VoteResDTO result = voteService.handleVote(testVoteReqDTO, testUser);

        assertNull(result.voteType());
        assertEquals(4, result.voteCount());
        verify(voteRepository).delete(existingUpVote);
        verify(postRepository).save(testPost);
    }

    @Test
    void handleVote_ExistingOppositeVote_ShouldUpdateVoteAndAdjustCount() {
        VoteReqDTO upVoteReq = new VoteReqDTO(1, VoteType.UP);
        when(postRepository.findById(1)).thenReturn(Optional.of(testPost));
        when(voteRepository.findByPostAndUser(testPost, testUser)).thenReturn(Optional.of(existingDownVote));
        when(voteRepository.save(existingDownVote)).thenReturn(existingDownVote);

        VoteResDTO result = voteService.handleVote(upVoteReq, testUser);

        assertEquals(VoteType.UP, result.voteType());
        assertEquals(7, result.voteCount());
        verify(voteRepository).save(existingDownVote);
        verify(postRepository).save(testPost);
    }

    @Test
    void handleVote_PostNotFound_ShouldThrowException() {
        when(postRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            voteService.handleVote(testVoteReqDTO, testUser);
        });
    }


    @Test
    void create_ValidVote_ShouldReturnVoteResDTO() {
        Vote newVote = Vote.builder()
                .post(testPost)
                .user(testUser)
                .voteType(VoteType.UP)
                .build();

        Vote savedVote = Vote.builder()
                .id(1)
                .post(testPost)
                .user(testUser)
                .voteType(VoteType.UP)
                .build();

        when(voteMapper.fromRequestDTO(testVoteReqDTO)).thenReturn(newVote);
        when(voteRepository.save(newVote)).thenReturn(savedVote);
        when(voteMapper.toResponseDTO(savedVote)).thenReturn(
                new VoteResDTO(1, 1, 1, VoteType.UP, 0));

        VoteResDTO result = voteService.create(testVoteReqDTO);

        assertNotNull(result);
        assertEquals(1, result.id());
        assertEquals(VoteType.UP, result.voteType());
        verify(voteRepository).save(newVote);
    }

    @Test
    void readById_ExistingVote_ShouldReturnVoteResDTO() {
        when(voteRepository.findById(1)).thenReturn(Optional.of(existingUpVote));
        when(voteMapper.toResponseDTO(existingUpVote)).thenReturn(
                new VoteResDTO(1, 1, 1, VoteType.UP, 5));

        VoteResDTO result = voteService.readById(1);

        assertNotNull(result);
        assertEquals(1, result.id());
        assertEquals(VoteType.UP, result.voteType());
    }

    @Test
    void readById_NonExistingVote_ShouldThrowException() {
        when(voteRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            voteService.readById(999);
        });
    }

    @Test
    void update_ExistingVote_ShouldReturnUpdatedVoteResDTO() {
        VoteReqDTO updateDTO = new VoteReqDTO(1, VoteType.DOWN);
        when(voteRepository.findById(1)).thenReturn(Optional.of(existingUpVote));
        when(voteRepository.save(existingUpVote)).thenReturn(existingUpVote);
        when(voteMapper.toResponseDTO(existingUpVote)).thenReturn(
                new VoteResDTO(1, 1, 1, VoteType.DOWN, 5));

        VoteResDTO result = voteService.update(1, updateDTO);

        assertNotNull(result);
        assertEquals(VoteType.DOWN, result.voteType());
        verify(voteMapper).updateEntity(updateDTO, existingUpVote);
    }

    @Test
    void delete_ExistingVote_ShouldDeleteVote() {
        when(voteRepository.findById(1)).thenReturn(Optional.of(existingUpVote));

        voteService.delete(1);

        verify(voteRepository).delete(existingUpVote);
    }
}
