package org.filrouge.gymcommunity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record SimpleSuccessDTO(
        LocalDateTime timestamp,
        int status,
        String message,
        Object data,
        PageDTO pagination
) {}