package org.filrouge.gymcommunity.dto.user;


public record UserResDTO(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String city,
        String profilePicture,
        String bannerPicture
) {}
