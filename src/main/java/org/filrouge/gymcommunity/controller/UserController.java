package org.filrouge.gymcommunity.controller;

import org.filrouge.gymcommunity.dto.user.UserReqDTO;
import org.filrouge.gymcommunity.dto.user.UserResDTO;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.service.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends GenericController<UserResDTO, UserReqDTO, AppUser, Integer> {
    public UserController(UserService userService) {
        super(userService, AppUser.class);
    }

}
