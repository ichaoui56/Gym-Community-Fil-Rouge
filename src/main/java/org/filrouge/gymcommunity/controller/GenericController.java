package org.filrouge.gymcommunity.controller;

import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.model.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.filrouge.gymcommunity.dto.SimpleSuccessDTO;
import org.filrouge.gymcommunity.service.CrudService;
import org.filrouge.gymcommunity.marker.validation.OnCreate;

import static org.filrouge.gymcommunity.helper.ReflectionUtil.getClassName;
import static org.filrouge.gymcommunity.response.Response.simpleSuccess;

@RequiredArgsConstructor
@Validated
public abstract class GenericController<RES, REQ, T extends BaseEntity<ID>, ID> {

    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_SIZE = "10";

    protected final CrudService<RES, REQ, T, ID> genericService;
    protected final Class<T> entityClass;

    @GetMapping("/get/{id}")
    public ResponseEntity<SimpleSuccessDTO> handleRead(@PathVariable ID id) {
        RES responseDTO = genericService.readById(id);
        return simpleSuccess(200, getName() + " retrieved successfully.", responseDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<SimpleSuccessDTO> handleReadAll(@RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                                          @RequestParam(defaultValue = DEFAULT_SIZE) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<RES> responseDTO = genericService.readAll(pageable);
        return simpleSuccess(200, getName() + " retrieved successfully.", responseDTO);
    }

    @PostMapping("/new")
    public ResponseEntity<SimpleSuccessDTO> handleCreate(@RequestBody @Validated(OnCreate.class) REQ request) {
        RES responseDTO = genericService.create(request);
        return simpleSuccess(201, getName() + " created successfully.", responseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SimpleSuccessDTO> handleUpdate(@PathVariable("id") ID id, @RequestBody REQ request) {
        RES responseDTO = genericService.update(id, request);
        return simpleSuccess(200, getName() + " updated successfully.", responseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SimpleSuccessDTO> handleDelete(@PathVariable("id") ID id) {
        genericService.delete(id);
        return simpleSuccess(200, getName() + " deleted successfully.");
    }

    protected String getName() {
        return getClassName(entityClass);
    }
}
