package com.example.onlinemedicalregistrationback.notFoundExceptions;


public class DoctorNotFoundException extends RuntimeException{
    public DoctorNotFoundException(long doctorId){super("Doctor not found, id = " + doctorId);}
}
