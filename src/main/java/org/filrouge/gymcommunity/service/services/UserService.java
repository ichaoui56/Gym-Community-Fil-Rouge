package org.filrouge.gymcommunity.service.services;

import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.dto.user.UserReqDTO;
import org.filrouge.gymcommunity.dto.user.UserResDTO;
import org.filrouge.gymcommunity.dto.userNutr.UserNutritionReqDTO;
import org.filrouge.gymcommunity.dto.userNutr.UserNutritionResDTO;
import org.filrouge.gymcommunity.exception.UserAlreadyExistsException;
import org.filrouge.gymcommunity.exception.UserPhoneAlreadyExistsException;
import org.filrouge.gymcommunity.helper.calculeNutrition.CalorieCalculator;
import org.filrouge.gymcommunity.mapper.GenericMapper;
import org.filrouge.gymcommunity.mapper.UserMapper;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.repository.GenericRepository;
import org.filrouge.gymcommunity.repository.UserRepository;
import org.filrouge.gymcommunity.service.GenericServiceImpl;
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
        System.out.println("userService: " + appUser);
        return User.withUsername(appUser.getEmail())
                .password(appUser.getPassword())
//                .roles("USER")
                .build();
    }

}
