package com.example.onlinemedicalregistrationback.controller;


import com.example.onlinemedicalregistrationback.dto.AllDoctorsRequestBody;
import com.example.onlinemedicalregistrationback.dto.CreateRecordRequestBody;
import com.example.onlinemedicalregistrationback.dto.GetAvailableRecordsRequestBody;
import com.example.onlinemedicalregistrationback.dto.SearchRequestBody;
import com.example.onlinemedicalregistrationback.dto.VerifyCancelRequestBody;
import com.example.onlinemedicalregistrationback.service.DoctorService;
import com.example.onlinemedicalregistrationback.exception.RecordNotFoundException;
import com.example.onlinemedicalregistrationback.model.Doctor;
import com.example.onlinemedicalregistrationback.model.Record;
import com.example.onlinemedicalregistrationback.exception.DoctorNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.onlinemedicalregistrationback.repository.DoctorRepository;
import com.example.onlinemedicalregistrationback.repository.RecordRepository;
import com.example.onlinemedicalregistrationback.dto.Responses;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@ResponseBody
@AllArgsConstructor
public class DoctorController {
    public final DoctorRepository doctorRepository;
    public final DoctorService doctorService;
    public final RecordRepository recordRepository;


    @PostMapping("/records")
    Responses.GetAvailableRecordsResponseBody getRecords (
        @RequestBody GetAvailableRecordsRequestBody body
    ){
        return doctorService.getRecords(body);
    }

    @PostMapping("/create_record")
    void createRecord (@RequestBody CreateRecordRequestBody createRecordRequestBody){
        Record record;
        record = recordRepository.findById(createRecordRequestBody.getRecordId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Such record doesn't exist!"));
        Doctor doctor = doctorRepository.findById(createRecordRequestBody.getDoctorId())
            .orElseThrow(() -> new DoctorNotFoundException(createRecordRequestBody.getDoctorId()));

        Set<Record> doctorRecords = doctor.getRecords();
        doctorRecords.remove(record);
        doctorRepository.save(doctor);
    }

    @PostMapping("/all_doctors")
    Responses.AllDoctorResponseBody allDoctors(@RequestBody AllDoctorsRequestBody allDoctorsRequestBody){
        return doctorService.allDoctors(allDoctorsRequestBody);
    }

    @PostMapping("/search")
    Responses.SearchResponseBody search (@RequestBody SearchRequestBody searchRequestBody){
        return doctorService.search(searchRequestBody);
    }

    @PostMapping("/verify_cancel")
    void verifyCancel (@RequestBody VerifyCancelRequestBody verifyCancelRequestBody){
    }
}
