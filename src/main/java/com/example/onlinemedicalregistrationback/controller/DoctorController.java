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

    @PostMapping("/doctor")
    asass addDoctor(@RequestBody addDoc addDoc){
        Set<Record> records = new HashSet<>();
        for(int i = 0; i < 10; ++i){
            records.add(new Record(LocalDateTime.now().plusHours(i)));
        }
        recordRepository.saveAll(records);
        Doctor doctor = new Doctor(addDoc.name,addDoc.organization, records);
        doctorRepository.save(doctor);
        return new asass("Zhopa");
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

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class asass implements Serializable{
        String zhopa;
    }
}
