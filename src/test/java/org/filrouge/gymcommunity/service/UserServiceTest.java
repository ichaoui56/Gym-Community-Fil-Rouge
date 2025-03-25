package org.filrouge.gymcommunity.service;

import org.filrouge.gymcommunity.dto.auth.AuthReqDTO;
import org.filrouge.gymcommunity.dto.user.UserReqDTO;
import org.filrouge.gymcommunity.dto.user.UserResDTO;
import org.filrouge.gymcommunity.exception.UserAlreadyExistsException;
import org.filrouge.gymcommunity.exception.UserPhoneAlreadyExistsException;
import org.filrouge.gymcommunity.mapper.UserMapper;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.repository.UserRepository;
import org.filrouge.gymcommunity.security.JwtService;
import org.filrouge.gymcommunity.service.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private UserMapper userMapper;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private AuthenticationManager authenticationManager;
    @Mock private JwtService jwtService;

    @InjectMocks
    private UserService userService;

    private AppUser testUser;
    private UserReqDTO testUserReqDTO;
    private AuthReqDTO testAuthReqDTO;
    private UserResDTO testUserResDTO;

    @BeforeEach
    void setUp() {
        testUser = AppUser.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password("encodedPassword")
                .phone("1234567890")
                .city("Paris")
                .build();

        testUserReqDTO = new UserReqDTO(
                "John",
                "Doe",
                "john.doe@example.com",
                "password123",
                "1234567890",
                "Paris",
                null,
                null
        );

        testUserResDTO = new UserResDTO(
                1,
                "John",
                "Doe",
                "john.doe@example.com",
                "1234567890",
                "Paris",
                null,
                null
        );

        testAuthReqDTO = new AuthReqDTO("john.doe@example.com", "password123");
    }

    @Test
    void authenticate_WithValidCredentials_ReturnsToken() {
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtService.generateToken(authentication)).thenReturn("testToken");

        String result = userService.authenticate(testAuthReqDTO);

        assertEquals("testToken", result);
        verify(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken(
                        testAuthReqDTO.email(),
                        testAuthReqDTO.password()
                )
        );
    }

    @Test
    void authenticate_WithInvalidCredentials_ThrowsException() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        assertThrows(BadCredentialsException.class, () -> {
            userService.authenticate(testAuthReqDTO);
        });
    }

    @Test
    void findByEmail_WithExistingEmail_ReturnsUserResDTO() {
        when(userRepository.findByEmail(testUser.getEmail()))
                .thenReturn(Optional.of(testUser));
        when(userMapper.toResponseDTO(testUser)).thenReturn(testUserResDTO);

        UserResDTO result = userService.findByEmail(testUser.getEmail());

        assertNotNull(result);
        assertEquals(testUser.getEmail(), result.email());
        verify(userRepository).findByEmail(testUser.getEmail());
    }

    @Test
    void findByEmail_WithNonExistingEmail_ThrowsException() {
        when(userRepository.findByEmail("nonexisting@example.com"))
                .thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.findByEmail("nonexisting@example.com");
        });
    }

    @Test
    void updatePassword_WithValidCurrentPassword_UpdatesPassword() {
        String currentPassword = "currentPassword";
        String newPassword = "newPassword";
        String encodedNewPassword = "encodedNewPassword";

        when(userRepository.findById(testUser.getId()))
                .thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches(currentPassword, testUser.getPassword()))
                .thenReturn(true);
        when(passwordEncoder.encode(newPassword)).thenReturn(encodedNewPassword);

        boolean result = userService.updatePassword(testUser.getId(), currentPassword, newPassword);

        assertTrue(result);
        assertEquals(encodedNewPassword, testUser.getPassword());
        verify(userRepository).save(testUser);
    }

    @Test
    void updatePassword_WithInvalidCurrentPassword_ReturnsFalse() {
        when(userRepository.findById(testUser.getId()))
                .thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches("wrongPassword", testUser.getPassword()))
                .thenReturn(false);

        boolean result = userService.updatePassword(testUser.getId(), "wrongPassword", "newPassword");

        assertFalse(result);
        verify(userRepository, never()).save(any());
    }

    @Test
    void updatePassword_WithNonExistingUser_ReturnsFalse() {
        when(userRepository.findById(999)).thenReturn(Optional.empty());

        boolean result = userService.updatePassword(999, "currentPassword", "newPassword");

        assertFalse(result);
    }

    @Test
    void create_WithValidUser_ReturnsUserResDTO() {
        AppUser mappedUser = AppUser.builder()
                .firstName(testUserReqDTO.firstName())
                .lastName(testUserReqDTO.lastName())
                .email(testUserReqDTO.email())
                .password(testUserReqDTO.password())
                .phone(testUserReqDTO.phone())
                .city(testUserReqDTO.city())
                .build();

        AppUser savedUser = AppUser.builder()
                .id(1)
                .firstName(testUserReqDTO.firstName())
                .lastName(testUserReqDTO.lastName())
                .email(testUserReqDTO.email())
                .password("encodedPassword")
                .phone(testUserReqDTO.phone())
                .city(testUserReqDTO.city())
                .profilePicture("path/to/avatar.png")
                .bannerPicture("path/to/pattern.webp")
                .build();

        when(userMapper.fromRequestDTO(testUserReqDTO)).thenReturn(mappedUser);
        when(userRepository.save(mappedUser)).thenReturn(savedUser);
        when(userMapper.toResponseDTO(savedUser)).thenReturn(testUserResDTO);

        UserResDTO result = userService.create(testUserReqDTO);

        assertNotNull(result);
        assertEquals(testUserReqDTO.email(), result.email());

        verify(userMapper).fromRequestDTO(testUserReqDTO);
        verify(userRepository).save(mappedUser);
        verify(userMapper).toResponseDTO(savedUser);

        verify(passwordEncoder, never()).encode(any());
    }

    @Test
    void readById_WithExistingId_ReturnsUserResDTO() {
        when(userRepository.findById(testUser.getId()))
                .thenReturn(Optional.of(testUser));
        when(userMapper.toResponseDTO(testUser)).thenReturn(testUserResDTO);

        UserResDTO result = userService.readById(testUser.getId());

        assertNotNull(result);
        assertEquals(testUser.getId(), result.id());
        verify(userRepository).findById(testUser.getId());
    }

    @Test
    void readById_WithNonExistingId_ThrowsException() {
        when(userRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            userService.readById(999);
        });
    }

    @Test
    void readAll_ReturnsPageOfUserResDTO() {
        Page<AppUser> userPage = new PageImpl<>(List.of(testUser));
        when(userRepository.findAll(any(Pageable.class))).thenReturn(userPage);
        when(userMapper.toResponseDTO(testUser)).thenReturn(testUserResDTO);

        Page<UserResDTO> result = userService.readAll(PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(userRepository).findAll(any(Pageable.class));
    }

    @Test
    void update_WithNonExistingId_ThrowsException() {
        when(userRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            userService.update(999, testUserReqDTO);
        });
    }

    @Test
    void delete_WithExistingId_DeletesUser() {
        when(userRepository.findById(testUser.getId()))
                .thenReturn(Optional.of(testUser));

        userService.delete(testUser.getId());

        verify(userRepository).delete(testUser);
    }

    @Test
    void delete_WithNonExistingId_ThrowsException() {
        when(userRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            userService.delete(999);
        });
    }
}