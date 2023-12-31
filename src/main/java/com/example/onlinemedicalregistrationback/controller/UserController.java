package com.example.onlinemedicalregistrationback.controller;


import com.example.onlinemedicalregistrationback.dto.SignInRequestBody;
import com.example.onlinemedicalregistrationback.dto.SignUpRequestBody;
import com.example.onlinemedicalregistrationback.dto.VerifyRequestBody;
import com.example.onlinemedicalregistrationback.dto.Responses;
import com.example.onlinemedicalregistrationback.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@RestController
@AllArgsConstructor
public class UserController {
    public final UserService userService;

    @PostMapping("/verify")
    void verify(@RequestBody VerifyRequestBody verifyRequestBody){
        userService.verify(verifyRequestBody);
    }

    @PostMapping("/sign_up")
    void signUp (@RequestBody SignUpRequestBody signUpRequestBody){
        userService.signUp(signUpRequestBody);
    }

    @PostMapping("sign_in")
    void signIn (@RequestBody SignInRequestBody signInRequestBody){
        userService.signIn(signInRequestBody);
    }

}
