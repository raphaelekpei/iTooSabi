package com.raphael.itoosabi.service;


import com.raphael.itoosabi.dto.request.LoginUserRequest;
import com.raphael.itoosabi.dto.request.RegisterUserRequest;
import com.raphael.itoosabi.dto.response.LoginUserResponse;
import com.raphael.itoosabi.dto.response.RegisterUserResponse;

import java.io.IOException;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) throws IOException;

    LoginUserResponse loginUser(LoginUserRequest loginUserRequest);
}
