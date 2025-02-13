package org.filrouge.gymcommunity.controller;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthReqDTO request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        String token = jwtService.generateToken(authentication);
        return Response.simpleSuccess(200, "Logged in successfully", token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserReqDTO request) {
        System.out.println("Registering user with email: " + request.email()); // Debug log
        UserResDTO newUser = userService.create(request);
        return Response.simpleSuccess(200, "Registration successful");
    }
}
