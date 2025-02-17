package org.filrouge.gymcommunity.helper;

import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.model.entity.Admin;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.repository.AdminRepository;
import org.filrouge.gymcommunity.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Helper class for security-related operations.
 */
@Component
@RequiredArgsConstructor
public class SecurityHelper {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    /**
     * Retrieves the authenticated user from the security context.
     *
     * @return The authenticated AppUser.
     * @throws UsernameNotFoundException if the authenticated user cannot be found.
     */
    public AppUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("No authenticated user found.");
        }

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Authenticated user with email " + email + " not found"));
    }

    /**
     * Retrieves the authenticated admin from the security context.
     *
     * @return The authenticated Admin.
     * @throws UsernameNotFoundException if the authenticated admin cannot be found.
     */
    public Admin getAuthenticatedAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("No authenticated admin found.");
        }

        String email = authentication.getName();

        return adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Authenticated admin with email " + email + " not found"));
    }

}
