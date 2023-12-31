package com.example.onlinemedicalregistrationback.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DoctorNotFoundException extends ResponseStatusException {
    public DoctorNotFoundException(long doctorId){
        super(HttpStatus.NOT_FOUND, "Doctor not found, id = " + doctorId);
    }
}
