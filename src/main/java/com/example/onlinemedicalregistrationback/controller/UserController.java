package com.example.onlinemedicalregistrationback.controller;


import com.example.onlinemedicalregistrationback.model.User;
import com.example.onlinemedicalregistrationback.repository.UserRepository;
import com.example.onlinemedicalregistrationback.serializableClasses.Requests;
import com.example.onlinemedicalregistrationback.serializableClasses.Responses;
import com.example.onlinemedicalregistrationback.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@ResponseBody
@RestController
public class UserController {
    UserRepository userRepository;
    public final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/verify")
    Responses.VerifyResponseBody verifying (@RequestBody Requests.VerifyRequestBody verifyRequestBody){
        return userService.verifying(verifyRequestBody);
    }

    @PostMapping("/sign_up")
    Responses.SignUpResponseBody signUp (@RequestBody Requests.SignUpRequestBody signUpRequestBody){
        return userService.signUp(signUpRequestBody);
    }

    @PostMapping("sign_in")
    Responses.SignInResponseBody signIn (@RequestBody Requests.SignInRequestBody signInRequestBody){
        return userService.signIn(signInRequestBody);
    }

}
