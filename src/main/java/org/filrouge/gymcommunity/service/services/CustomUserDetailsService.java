package org.filrouge.gymcommunity.service.services;

import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.model.entity.Admin;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.repository.AdminRepository;
import org.filrouge.gymcommunity.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Admin admin = adminRepository.findByEmail(email).orElse(null);
        if (admin != null) {
            return User.withUsername(admin.getEmail())
                    .password(admin.getPassword())
                    .roles(admin.getRole())
                    .build();
        }

        AppUser appUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return User.withUsername(appUser.getEmail())
                .password(appUser.getPassword())
                .roles(appUser.getRole())
                .build();

    }
}
