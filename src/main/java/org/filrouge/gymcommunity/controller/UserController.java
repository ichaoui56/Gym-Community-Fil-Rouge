package org.filrouge.gymcommunity.controller;

import org.filrouge.gymcommunity.dto.SimpleSuccessDTO;
import org.filrouge.gymcommunity.dto.user.UserReqDTO;
import org.filrouge.gymcommunity.dto.user.UserResDTO;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.service.services.FileStorageService;
import org.filrouge.gymcommunity.service.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static org.filrouge.gymcommunity.response.Response.simpleSuccess;

@RestController
@RequestMapping("/user")
public class UserController extends GenericController<UserResDTO, UserReqDTO, AppUser, Integer> {

    private final UserService userService;
    private final FileStorageService fileStorageService;

    public UserController(UserService userService, FileStorageService fileStorageService) {
        super(userService, AppUser.class);
        this.userService = userService;
        this.fileStorageService = fileStorageService;
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

    @PutMapping("/updateProfile/{id}")
    public ResponseEntity<?> updateProfile(
            @PathVariable Integer id,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "avatar", required = false) MultipartFile profilePicture,
            @RequestParam(value = "banner", required = false) MultipartFile bannerPicture
    ) {
        // Get the current user data first
        UserResDTO currentUser = userService.readById(id);

        // Save the uploaded files and get their paths
        String profilePicturePath = (profilePicture != null) ? fileStorageService.store(profilePicture) : currentUser.profilePicture();
        String bannerPicturePath = (bannerPicture != null) ? fileStorageService.store(bannerPicture) : currentUser.bannerPicture();

        // Update the UserReqDTO with the new values or keep existing ones
        UserReqDTO updatedRequest = new UserReqDTO(
                firstName != null ? firstName : currentUser.firstName(),
                lastName != null ? lastName : currentUser.lastName(),
                email != null ? email : currentUser.email(),
                phone != null ? phone : currentUser.phone(),
                password != null ? password : null, // Password is a special case
                city != null ? city : currentUser.city(),
                profilePicturePath,
                bannerPicturePath
        );

        // Call the service to update the user
        UserResDTO responseDTO = userService.update(id, updatedRequest);
        return simpleSuccess(200, "User updated successfully.", responseDTO);
    }

    // Add to UserController.java
    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<?> updatePassword(
            @PathVariable Integer id,
            @RequestBody Map<String, String> passwordData
    ) {
        String currentPassword = passwordData.get("currentPassword");
        String newPassword = passwordData.get("newPassword");

        if (currentPassword == null || newPassword == null) {
            return ResponseEntity.badRequest().body("Current password and new password are required");
        }

        boolean updated = userService.updatePassword(id, currentPassword, newPassword);
        if (updated) {
            return simpleSuccess(200, "Password updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Current password is incorrect");
        }
    }

}
