package org.filrouge.gymcommunity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorDTO(

        LocalDateTime timestamp,
        int status,
        String message,
        Map<String, String> errors

) {
}