package com.example.onlinemedicalregistrationback.service;

import com.example.onlinemedicalregistrationback.model.User;
import com.example.onlinemedicalregistrationback.repository.UserRepository;
import com.example.onlinemedicalregistrationback.serializableClasses.Requests;
import com.example.onlinemedicalregistrationback.serializableClasses.Responses;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Responses.VerifyResponseBody verifying (Requests.VerifyRequestBody verifyRequestBody){
        if (verifyRequestBody.getPolicy().length() != 16){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Номер полиса неверный или такой полис не зарегистрирован. Проверьте данные и попробуйте снова. Для справок обращаться по тел. 122 или на сайт mos.ru.");
        }
        else if (verifyRequestBody.getPassport().length() != 10){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Номер паспорта неверный или такой полис не зарегистрирован. Проверьте данные и попробуйте снова. Для справок обращаться по тел. 122 или на сайт mos.ru.");
        }
        User newUser = new User(verifyRequestBody.getPassport(), "NULL", verifyRequestBody.getPolicy());
        userRepository.save(newUser);
        return new Responses.VerifyResponseBody("OK");
    }

    public Responses.SignUpResponseBody signUp (Requests.SignUpRequestBody signUpRequestBody){
        userRepository.setPassword(signUpRequestBody.getPassword(), signUpRequestBody.getPolicy());
        return new Responses.SignUpResponseBody("OK");
    }

    public Responses.SignInResponseBody signIn (Requests.SignInRequestBody signInRequestBody){
        User user = userRepository.findByPolicy(signInRequestBody.getPolicy());
        if (Objects.isNull(user)){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Неверный номер полиса! Уточните данные или пройдите процедуру регистрации.");
        }
        if (!Objects.equals(signInRequestBody.getPassword(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Неправильный пароль!");
        }
        return new Responses.SignInResponseBody("OK");
    }
}
