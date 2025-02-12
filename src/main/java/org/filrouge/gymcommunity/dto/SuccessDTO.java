package org.filrouge.gymcommunity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SuccessDTO(
        LocalDateTime timestamp,
        int status,
        String message,
        Map<String, Object> data
) {}