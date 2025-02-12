package org.filrouge.gymcommunity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.filrouge.gymcommunity.dto.SimpleSuccessDTO;

import static org.filrouge.gymcommunity.response.Response.simpleSuccess;

@Validated
public interface ReadController<RES, REQ, T, ID> extends BaseController<RES, REQ, T, ID> {
    @GetMapping("/get/{id}")
    default ResponseEntity<SimpleSuccessDTO> handleRead(@PathVariable ID id) {
        RES responseDTO = getService().readById(id);
        return simpleSuccess(200, getEntityClass().getSimpleName() + " retrieved successfully.", responseDTO);
    }
}