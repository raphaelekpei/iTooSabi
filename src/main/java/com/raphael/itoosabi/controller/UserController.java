package com.raphael.itoosabi.controller;


import com.raphael.itoosabi.dto.request.LoginUserRequest;
import com.raphael.itoosabi.dto.request.RegisterUserRequest;
import com.raphael.itoosabi.dto.response.LoginUserResponse;
import com.raphael.itoosabi.dto.response.RegisterUserResponse;
import com.raphael.itoosabi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest) throws IOException {
        return ResponseEntity.ok(userService.registerUser(registerUserRequest));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LoginUserResponse> loginUser(@RequestBody LoginUserRequest loginUserRequest){
        return ResponseEntity.ok(userService.loginUser(loginUserRequest));
    }
}
