package com.example.onlinemedicalregistrationback.notFoundExceptions;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(long recordId){super("Record not found, id = " + recordId);}
}
