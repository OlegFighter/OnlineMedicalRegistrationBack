package com.example.onlinemedicalregistrationback.controller;

import lombok.*;
import com.example.onlinemedicalregistrationback.model.Doctor;
import com.example.onlinemedicalregistrationback.model.Record;
import com.example.onlinemedicalregistrationback.notFoundExceptions.DoctorNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.onlinemedicalregistrationback.repository.DoctorRepository;
import com.example.onlinemedicalregistrationback.repository.RecordRepository;
import com.example.onlinemedicalregistrationback.serializableClasses.Requests;
import com.example.onlinemedicalregistrationback.serializableClasses.Responses;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

@RestController
@ResponseBody
public class DoctorController {
    public final DoctorRepository doctorRepository;
    public final RecordRepository recordRepository;

    public DoctorController(DoctorRepository doctorRepository, RecordRepository recordRepository) {
        this.doctorRepository = doctorRepository;
        this.recordRepository = recordRepository;
    }

    @PostMapping("/available_records")
    Responses.GetAvailableRecordsResponseBody getAvailableRecords(@RequestBody Requests.GetAvailableRecordsRequestBody getAvailableRecordsRequestBody){
        Doctor doctor = doctorRepository.findById(getAvailableRecordsRequestBody.getDoctorId()).
                orElseThrow(() -> new DoctorNotFoundException(getAvailableRecordsRequestBody.getDoctorId()));
        return new Responses.GetAvailableRecordsResponseBody(doctor.getRecords());
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class addDoc implements Serializable{
        String name;
        String organization;
    }

}
