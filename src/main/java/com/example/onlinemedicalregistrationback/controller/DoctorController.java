package com.example.onlinemedicalregistrationback.controller;


import com.example.onlinemedicalregistrationback.service.DoctorService;
import com.example.onlinemedicalregistrationback.notFoundExceptions.RecordNotFoundException;
import com.example.onlinemedicalregistrationback.model.Doctor;
import com.example.onlinemedicalregistrationback.model.Record;
import com.example.onlinemedicalregistrationback.notFoundExceptions.DoctorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.onlinemedicalregistrationback.repository.DoctorRepository;
import com.example.onlinemedicalregistrationback.repository.RecordRepository;
import com.example.onlinemedicalregistrationback.serializableClasses.Requests;
import com.example.onlinemedicalregistrationback.serializableClasses.Responses;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@ResponseBody
public class DoctorController {
    public final DoctorRepository doctorRepository;

    public final DoctorService doctorService;
    public final RecordRepository recordRepository;

    public DoctorController(DoctorRepository doctorRepository, DoctorService doctorService, RecordRepository recordRepository) {
        this.doctorRepository = doctorRepository;
        this.doctorService = doctorService;
        this.recordRepository = recordRepository;
    }

    @PostMapping("/records")
    Responses.GetAvailableRecordsResponseBody getRecords (@RequestBody Requests.GetAvailableRecordsRequestBody getAvailableRecordsRequestBody){
        return doctorService.getRecords(getAvailableRecordsRequestBody);
    }

    @PostMapping("/create_record")
    Responses.CreateRecordResponseBody createRecord (@RequestBody Requests.CreateRecordRequestBody createRecordRequestBody){
        Record record;
        try{
            record = recordRepository.findById(createRecordRequestBody.getRecordId()).orElseThrow(() -> new RecordNotFoundException(createRecordRequestBody.getRecordId()));
        }
        catch (RecordNotFoundException e){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Such record doesn't exist!");
        }
        Doctor doctor = doctorRepository.findById(createRecordRequestBody.getDoctorId()).orElseThrow(() -> new DoctorNotFoundException(createRecordRequestBody.getDoctorId()));

        Set<Record> doctorRecords = doctor.getRecords();
        doctorRecords.remove(record);
        doctorRepository.save(doctor);
        return new Responses.CreateRecordResponseBody("Ok");
    }

    @PostMapping("/all_doctors")
    Responses.AllDoctorResponseBody allDoctors(@RequestBody Requests.AllDoctorsRequestBody allDoctorsRequestBody){
        return doctorService.allDoctors(allDoctorsRequestBody);
    }

    @PostMapping("/search")
    Responses.SearchResponseBody search (@RequestBody Requests.SearchRequestBody searchRequestBody){
        return doctorService.search(searchRequestBody);
    }

    @PostMapping("/verify_cancel")
    void verifyCancel (@RequestBody Requests.VerifyCancelRequestBody verifyCancelRequestBody){}
}
