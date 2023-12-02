package com.example.onlinemedicalregistrationback.controller;


import com.example.onlinemedicalregistrationback.repository.UserRepository;
import com.example.onlinemedicalregistrationback.serializableClasses.Requests;
import com.example.onlinemedicalregistrationback.serializableClasses.Responses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@ResponseBody
@RestController
public class UserController {
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/verify")
    Responses.VerifyResponseBody verifying (@RequestBody Requests.VerifyRequestBody verifyRequestBody){
        if (verifyRequestBody.getPolicy().length() != 16){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Номер полиса неверный или такой полис не зарегистрирован. Проверьте данные и попробуйте снова. Для справок обращаться по тел. 122 или на сайт mos.ru.");
        }
        else if (verifyRequestBody.getPassport().length() != 10){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Номер паспорта неверный или такой полис не зарегистрирован. Проверьте данные и попробуйте снова. Для справок обращаться по тел. 122 или на сайт mos.ru.");
        }
        else return new Responses.VerifyResponseBody("OK");
    }
}
