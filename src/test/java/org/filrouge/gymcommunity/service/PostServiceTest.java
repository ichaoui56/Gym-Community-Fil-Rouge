package org.filrouge.gymcommunity.service;

import jakarta.persistence.EntityNotFoundException;
import org.filrouge.gymcommunity.dto.post.PostReqDTO;
import org.filrouge.gymcommunity.dto.post.PostResDTO;
import org.filrouge.gymcommunity.mapper.PostMapper;
import org.filrouge.gymcommunity.model.VoteType;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.Forum;
import org.filrouge.gymcommunity.model.entity.Post;
import org.filrouge.gymcommunity.model.entity.Vote;
import org.filrouge.gymcommunity.repository.PostRepository;
import org.filrouge.gymcommunity.repository.VoteRepository;
import org.filrouge.gymcommunity.service.services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private PostMapper postMapper;

    @InjectMocks
    private PostService postService;

    private Post testPost;
    private PostReqDTO testPostReqDTO;
    private PostResDTO testPostResDTO;
    private AppUser testUser;
    private Forum testForum;

    @BeforeEach
    void setUp() {
        testUser = AppUser.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .build();

        testForum = Forum.builder()
                .id(1)
                .title("Test Forum")
                .build();

        testPost = Post.builder()
                .id(1)
                .title("Test Post")
                .content("Test Content")
                .createdAt(LocalDateTime.now())
                .user(testUser)
                .forum(testForum)
                .voteCount(0)
                .build();

        testPostReqDTO = new PostReqDTO(
                "Test Post",
                1,
                "Test Content"
        );

        testPostResDTO = new PostResDTO(
                1,
                "Test Post",
                "Test Content",
                LocalDateTime.now(),
                0,
                null,
                Collections.emptyList(),
                null
        );
    }

    // Tests pour les méthodes héritées

    @Test
    void create_WithValidPost_ShouldReturnCreatedPost() {
        // Arrange
        when(postMapper.fromRequestDTO(testPostReqDTO)).thenReturn(testPost);
        when(postRepository.save(testPost)).thenReturn(testPost);
        when(postMapper.toResponseDTO(testPost)).thenReturn(testPostResDTO);

        // Act
        PostResDTO result = postService.create(testPostReqDTO);

        // Assert
        assertNotNull(result);
        assertEquals(testPostResDTO.id(), result.id());
        assertEquals(testPostResDTO.title(), result.title());
        verify(postRepository).save(testPost);
    }

    @Test
    void readById_WithExistingId_ShouldReturnPost() {
        // Arrange
        when(postRepository.findById(1)).thenReturn(Optional.of(testPost));
        when(postMapper.toResponseDTO(testPost)).thenReturn(testPostResDTO);

        // Act
        PostResDTO result = postService.readById(1);

        // Assert
        assertNotNull(result);
        assertEquals(testPostResDTO.id(), result.id());
        verify(postRepository).findById(1);
    }

    @Test
    void readById_WithNonExistingId_ShouldThrowException() {
        // Arrange
        when(postRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> {
            postService.readById(999);
        });
    }

    @Test
    void update_WithValidPost_ShouldReturnUpdatedPost() {
        // Arrange
        PostReqDTO updateDto = new PostReqDTO("Updated Title", 1, "Updated Content");
        Post updatedPost = Post.builder()
                .id(1)
                .title("Updated Title")
                .content("Updated Content")
                .createdAt(LocalDateTime.now())
                .user(testUser)
                .forum(testForum)
                .voteCount(0)
                .build();

        PostResDTO updatedResDto = new PostResDTO(
                1,
                "Updated Title",
                "Updated Content",
                LocalDateTime.now(),
                0,
                null,
                Collections.emptyList(),
                null
        );

        when(postRepository.findById(1)).thenReturn(Optional.of(testPost));
        when(postRepository.save(any(Post.class))).thenReturn(updatedPost);
        when(postMapper.toResponseDTO(updatedPost)).thenReturn(updatedResDto);

        // Act
        PostResDTO result = postService.update(1, updateDto);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Title", result.title());
        assertEquals("Updated Content", result.content());
        verify(postRepository).save(any(Post.class));
    }

    @Test
    void delete_WithExistingId_ShouldDeletePost() {
        // Arrange
        when(postRepository.findById(1)).thenReturn(Optional.of(testPost));

        // Act
        postService.delete(1);

        // Assert
        verify(postRepository).delete(testPost);
    }

    // Tests pour les méthodes spécifiques

    @Test
    void getPostsByUserId_ShouldReturnUserPosts() {
        // Arrange
        Page<Post> postPage = new PageImpl<>(List.of(testPost));
        when(postRepository.findByUser_Id(1, Pageable.unpaged())).thenReturn(postPage);
        when(postMapper.toResponseDTO(testPost)).thenReturn(testPostResDTO);

        // Act
        Page<PostResDTO> result = postService.getPostsByUserId(1, Pageable.unpaged());

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(postRepository).findByUser_Id(1, Pageable.unpaged());
    }

    @Test
    void getPostsByForumId_ShouldReturnForumPosts() {
        // Arrange
        Page<Post> postPage = new PageImpl<>(List.of(testPost));
        when(postRepository.findByForum_Id(1, Pageable.unpaged())).thenReturn(postPage);
        when(postMapper.toResponseDTO(testPost)).thenReturn(testPostResDTO);

        // Act
        Page<PostResDTO> result = postService.getPostsByForumId(1, Pageable.unpaged());

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(postRepository).findByForum_Id(1, Pageable.unpaged());
    }

    @Test
    void getVotedPostsByUser_ShouldReturnUpVotedPosts() {
        // Arrange
        Vote upVote = Vote.builder()
                .id(1)
                .user(testUser)
                .post(testPost)
                .voteType(VoteType.UP)
                .build();

        when(voteRepository.findByUser(testUser)).thenReturn(List.of(upVote));
        when(postMapper.toResponseDTO(testPost)).thenReturn(testPostResDTO);

        // Act
        List<PostResDTO> result = postService.getVotedPostsByUser(testUser);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(voteRepository).findByUser(testUser);
    }

    @Test
    void getUnvotedPostsByUser_ShouldReturnDownVotedPosts() {
        // Arrange
        Vote downVote = Vote.builder()
                .id(1)
                .user(testUser)
                .post(testPost)
                .voteType(VoteType.DOWN)
                .build();

        when(voteRepository.findByUser(testUser)).thenReturn(List.of(downVote));
        when(postMapper.toResponseDTO(testPost)).thenReturn(testPostResDTO);

        // Act
        List<PostResDTO> result = postService.getUnvotedPostsByUser(testUser);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(voteRepository).findByUser(testUser);
    }

    @Test
    void getVotedPostsByUser_WithNoVotes_ShouldReturnEmptyList() {
        // Arrange
        when(voteRepository.findByUser(testUser)).thenReturn(Collections.emptyList());

        // Act
        List<PostResDTO> result = postService.getVotedPostsByUser(testUser);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getUnvotedPostsByUser_WithNoVotes_ShouldReturnEmptyList() {
        // Arrange
        when(voteRepository.findByUser(testUser)).thenReturn(Collections.emptyList());

        // Act
        List<PostResDTO> result = postService.getUnvotedPostsByUser(testUser);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}