package org.filrouge.gymcommunity.dto.user;

import jakarta.validation.constraints.*;

public record UserReqDTO(
        @NotBlank(message = "First name is required")
        @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password,

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "\\d{10,15}", message = "Invalid phone number format")
        String phone,

        @NotBlank(message = "City is required")
        String city,

        String profilePicture,
        String bannerPicture
) {
}
