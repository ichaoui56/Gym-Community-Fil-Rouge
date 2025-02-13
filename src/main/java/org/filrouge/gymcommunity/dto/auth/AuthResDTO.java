package org.filrouge.gymcommunity.dto.auth;

import org.filrouge.gymcommunity.dto.user.UserResDTO;

public record AuthResDTO(
        String token,
        UserResDTO user
) {}
