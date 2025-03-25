package org.filrouge.gymcommunity.controller;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.filrouge.gymcommunity.dto.SimpleSuccessDTO;
import org.filrouge.gymcommunity.dto.auth.AuthReqDTO;
import org.filrouge.gymcommunity.dto.user.UserReqDTO;
import org.filrouge.gymcommunity.dto.user.UserResDTO;
import org.filrouge.gymcommunity.marker.validation.OnCreate;
import org.filrouge.gymcommunity.response.Response;
import org.filrouge.gymcommunity.security.JwtService;
import org.filrouge.gymcommunity.service.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import static org.apache.tomcat.util.net.openssl.OpenSSLStatus.getName;
import static org.filrouge.gymcommunity.response.Response.simpleSuccess;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody AuthReqDTO request) {
        String token = userService.authenticate(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/user/register")
    public ResponseEntity<SimpleSuccessDTO> handleCreate(@RequestBody @Validated(OnCreate.class) UserReqDTO request) {
        UserResDTO responseDTO = userService.create(request);
        return simpleSuccess(201, getName() + " created successfully.", responseDTO);
    }
}
