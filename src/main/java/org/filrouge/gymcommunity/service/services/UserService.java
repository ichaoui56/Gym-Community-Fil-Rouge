package org.filrouge.gymcommunity.service.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.dto.user.UserReqDTO;
import org.filrouge.gymcommunity.dto.user.UserResDTO;
import org.filrouge.gymcommunity.exception.ConflictException;
import org.filrouge.gymcommunity.mapper.GenericMapper;
import org.filrouge.gymcommunity.mapper.UserMapper;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.repository.GenericRepository;
import org.filrouge.gymcommunity.repository.UserRepository;
import org.filrouge.gymcommunity.service.GenericServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService extends GenericServiceImpl<UserResDTO, UserReqDTO, AppUser, Integer>
        implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public GenericRepository<AppUser, Integer> getRepository() {
        return userRepository;
    }

    @Override
    public GenericMapper<AppUser, UserResDTO, UserReqDTO> getMapper() {
        return userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        AppUser appUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email)); // Fix: Throw exception instead of returning null

        return User.withUsername(appUser.getEmail())
                .password(appUser.getPassword())
//                .roles("USER")
                .build();
    }

    @Override
    public UserResDTO create(UserReqDTO request) {
        if (userRepository.existsByEmail(request.email())) {
            System.out.println("Conflict detected: Email already exists"); // Debug log
            throw new IllegalStateException("Username or email already exists");
        }

        AppUser user = userMapper.fromRequestDTO(request);
        user.setPassword(passwordEncoder.encode(request.password()));

        AppUser savedUser = userRepository.save(user);
        return userMapper.toResponseDTO(savedUser);
    }


    public UserResDTO getCurrentUser(Authentication authentication) {
        AppUser user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userMapper.toResponseDTO(user);
    }

    public boolean isResourceOwner(Integer userId, Authentication authentication) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user.getEmail().equals(authentication.getName());
    }

    public UserResDTO getByEmail(String email) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return userMapper.toResponseDTO(user);
    }

}
