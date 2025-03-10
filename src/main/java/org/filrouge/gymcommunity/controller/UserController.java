package org.filrouge.gymcommunity.controller;

import org.filrouge.gymcommunity.dto.user.UserReqDTO;
import org.filrouge.gymcommunity.dto.user.UserResDTO;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.service.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends GenericController<UserResDTO, UserReqDTO, AppUser, Integer> {

    private final UserService userService;

    public UserController(UserService userService) {
        super(userService, AppUser.class);
        this.userService = userService;
    }


    @GetMapping("/me")
    public ResponseEntity<?> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        String email = authentication.getName();
        UserResDTO user = userService.findByEmail(email);

        return ResponseEntity.ok(user);
    }
}
