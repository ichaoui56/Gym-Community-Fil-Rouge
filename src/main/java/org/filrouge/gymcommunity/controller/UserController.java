package org.filrouge.gymcommunity.controller;

import jakarta.validation.Valid;
import org.filrouge.gymcommunity.dto.SimpleSuccessDTO;
import org.filrouge.gymcommunity.dto.SuccessDTO;
import org.filrouge.gymcommunity.dto.user.UserReqDTO;
import org.filrouge.gymcommunity.dto.user.UserResDTO;
import org.filrouge.gymcommunity.dto.userNutr.UserNutritionReqDTO;
import org.filrouge.gymcommunity.dto.userNutr.UserNutritionResDTO;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.response.Response;
import org.filrouge.gymcommunity.service.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends GenericController<UserResDTO, UserReqDTO, AppUser, Integer> {

    private final UserService userService;

    public UserController(UserService userService) {
        super(userService, AppUser.class);
        this.userService = userService;
    }

}
