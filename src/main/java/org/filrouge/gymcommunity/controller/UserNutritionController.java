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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nutrition")
public class UserNutritionController extends GenericController<UserNutritionResDTO, UserNutritionReqDTO, UserNutrition, Integer>{

    public UserNutritionService userNutritionService;

    public UserNutritionController(UserNutritionService userNutritionService) {
        super(userNutritionService, UserNutrition.class);
        this.userNutritionService = userNutritionService;
    }

    @PostMapping("/calculate-bmr")
    public ResponseEntity<SimpleSuccessDTO> handleCalculateBMR(@RequestBody @Valid UserNutritionReqDTO userNutritionReqDTO){
        UserNutritionResDTO userNutritionResDTO = userNutritionService.create(userNutritionReqDTO);
        return Response.simpleSuccess(200, "User Nutrition was added successfuly", userNutritionResDTO);
    }
}
