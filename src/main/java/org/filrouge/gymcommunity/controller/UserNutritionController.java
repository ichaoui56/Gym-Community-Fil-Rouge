package org.filrouge.gymcommunity.controller;

import jakarta.validation.Valid;
import org.filrouge.gymcommunity.dto.SimpleSuccessDTO;
import org.filrouge.gymcommunity.dto.userNutr.UserNutritionReqDTO;
import org.filrouge.gymcommunity.dto.userNutr.UserNutritionResDTO;
import org.filrouge.gymcommunity.model.entity.UserNutrition;
import org.filrouge.gymcommunity.response.Response;
import org.filrouge.gymcommunity.service.CrudService;
import org.filrouge.gymcommunity.service.services.UserNutritionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nutrition")
public class UserNutritionController extends GenericController<UserNutritionResDTO, UserNutritionReqDTO, UserNutrition, Integer>{

    private final UserNutritionService userNutritionService;

    public UserNutritionController(UserNutritionService userNutritionService) {
        super(userNutritionService, UserNutrition.class);

        this.userNutritionService = userNutritionService;

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<SimpleSuccessDTO> getUserNutrition(@PathVariable Integer userId) {
        UserNutritionResDTO nutrition = userNutritionService.findByUserId(userId);
        return Response.simpleSuccess(200, "User nutrition retrieved successfully.", nutrition);
    }
}
