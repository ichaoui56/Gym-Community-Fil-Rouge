package org.filrouge.gymcommunity.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthReqDTO(
        @NotBlank @Email String email,
        @NotBlank String password
) {}
