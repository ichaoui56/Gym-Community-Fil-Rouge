package org.filrouge.gymcommunity.controller;

import org.filrouge.gymcommunity.dto.user.UserReqDTO;
import org.filrouge.gymcommunity.dto.user.UserResDTO;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.service.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends GenericController<UserResDTO, UserReqDTO, AppUser, Integer> {

    private final UserService userService;

    public UserController(UserService userService) {
        super(userService, AppUser.class);
        this.userService = userService;
    }

}
