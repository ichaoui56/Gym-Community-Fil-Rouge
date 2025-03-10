package org.filrouge.gymcommunity.controller;

import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.dto.SimpleSuccessDTO;
import org.filrouge.gymcommunity.dto.icon.IconResDTO;
import org.filrouge.gymcommunity.model.entity.Icon;
import org.filrouge.gymcommunity.repository.IconRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.filrouge.gymcommunity.response.Response.simpleSuccess;

@RestController
@RequestMapping("/icon")
@RequiredArgsConstructor
public class IconController {
    private final IconRepository iconRepository;

    @GetMapping("/all")
    public ResponseEntity<SimpleSuccessDTO> getAllIcons() {
        List<Icon> responseDTO = iconRepository.findAll();
        return simpleSuccess(200, "Icons retrieved successfully.", responseDTO);
    }
}
