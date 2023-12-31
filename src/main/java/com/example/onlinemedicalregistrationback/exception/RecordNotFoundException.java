package com.example.onlinemedicalregistrationback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RecordNotFoundException extends ResponseStatusException {
    public RecordNotFoundException(long recordId){
        super(HttpStatus.NOT_FOUND, "Record not found, id = " + recordId);
    }
}
