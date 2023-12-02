package com.example.onlinemedicalregistrationback.controller;

import antlr.actions.python.CodeLexer;
import com.example.onlinemedicalregistrationback.notDBModel.DoctorView;
import com.example.onlinemedicalregistrationback.notFoundExceptions.RecordNotFoundException;
import lombok.*;
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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

@RestController
@ResponseBody
public class DoctorController {
    public final DoctorRepository doctorRepository;
    public final RecordRepository recordRepository;

    public DoctorController(DoctorRepository doctorRepository, RecordRepository recordRepository) {
        this.doctorRepository = doctorRepository;
        this.recordRepository = recordRepository;
    }

    @PostMapping("/records")
    Responses.GetAvailableRecordsResponseBody getRecords (@RequestBody Requests.GetAvailableRecordsRequestBody getAvailableRecordsRequestBody){
        ArrayList<Record> records = recordRepository.findByDoctor(getAvailableRecordsRequestBody.getDoctorId());
        records.sort(Record.COMPARE_BY_TIME);
        ArrayList<Record> output = new ArrayList<>();
        int index = getAvailableRecordsRequestBody.getIndexFrom();
        while (index < records.size() && index < getAvailableRecordsRequestBody.getIndexFrom()+10) {
            output.add(records.get(index));
            index++;
        }
        return new Responses.GetAvailableRecordsResponseBody(output);
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
        //recordRepository.deleteById(createRecordRequestBody.getRecordId());
        return new Responses.CreateRecordResponseBody("Ok");
    }

    @PostMapping("/all_doctors")
    Responses.AllDoctorResponseBody allDoctors(@RequestBody Requests.AllDoctorsRequestBody allDoctorsRequestBody){
        List<Doctor> doctors = doctorRepository.findAll();
        ArrayList<DoctorView> doctorsForResponse = new ArrayList<>();
        for (Doctor ent : doctors) {
            doctorsForResponse.add(new DoctorView(ent.getId(), ent.getName(), ent.getOrganization(), ent.getSpecialization()));
        }
        doctorsForResponse.sort(DoctorView.COMPARE_BY_NAME);
        int index = allDoctorsRequestBody.getIndexFrom();
        ArrayList<DoctorView> result = new ArrayList<>();
        while (index < doctorsForResponse.size() && index < allDoctorsRequestBody.getIndexFrom()+10){
            result.add(doctorsForResponse.get(index));
            index++;
        }
        int count = doctorsForResponse.size();
        return new Responses.AllDoctorResponseBody(result, count);
    }

    @PostMapping("/search")
    Responses.SearchResponseBody search (@RequestBody Requests.SearchRequestBody searchRequestBody){
        final String flag = "EMPTY_VALUE";
        ArrayList<Doctor> doctors = new ArrayList<>();
        if (!Objects.equals(searchRequestBody.getName(), flag)
                && !Objects.equals(searchRequestBody.getOrg(), flag)
                && !Objects.equals(searchRequestBody.getSpecialization(), flag)){
            doctors = doctorRepository.findByNameAndOrganizationAndSpecialization(searchRequestBody.getName(), searchRequestBody.getOrg(), searchRequestBody.getSpecialization());
        }
        else if (!Objects.equals(searchRequestBody.getName(), flag)
                && !Objects.equals(searchRequestBody.getOrg(), flag)
                && Objects.equals(searchRequestBody.getSpecialization(), flag)){
            doctors = doctorRepository.findByNameAndOrganization(searchRequestBody.getName(), searchRequestBody.getOrg());
        }
        else if (!Objects.equals(searchRequestBody.getName(), flag)
                && Objects.equals(searchRequestBody.getOrg(), flag)
                && !Objects.equals(searchRequestBody.getSpecialization(), flag)){
            doctors = doctorRepository.findByNameAndSpecialization(searchRequestBody.getName(), searchRequestBody.getSpecialization());
        }
        else if (Objects.equals(searchRequestBody.getName(), flag)
                && !Objects.equals(searchRequestBody.getOrg(), flag)
                && !Objects.equals(searchRequestBody.getSpecialization(), flag)){
            doctors = doctorRepository.findByOrganizationAndSpecialization(searchRequestBody.getSpecialization(), searchRequestBody.getOrg());
        }
        else if (!Objects.equals(searchRequestBody.getName(), flag)
                && Objects.equals(searchRequestBody.getOrg(), flag)
                && Objects.equals(searchRequestBody.getSpecialization(), flag)){
            doctors = doctorRepository.findByName(searchRequestBody.getName());
        }
        else if (Objects.equals(searchRequestBody.getName(), flag)
                && Objects.equals(searchRequestBody.getOrg(), flag)
                && !Objects.equals(searchRequestBody.getSpecialization(), flag)){
            doctors = doctorRepository.findBySpecialization(searchRequestBody.getSpecialization());
        }
        else if (Objects.equals(searchRequestBody.getName(), flag)
                && !Objects.equals(searchRequestBody.getOrg(), flag)
                && Objects.equals(searchRequestBody.getSpecialization(), flag)){
            doctors = doctorRepository.findByOrganization(searchRequestBody.getOrg());
        }
        ArrayList<DoctorView> doctorsForResponse = new ArrayList<>();
        for (Doctor ent : doctors) {
            doctorsForResponse.add(new DoctorView(ent.getId(), ent.getName(), ent.getOrganization(), ent.getSpecialization()));
        }
        doctorsForResponse.sort(DoctorView.COMPARE_BY_NAME);
        int index = searchRequestBody.getIndexFrom();
        ArrayList<DoctorView> result = new ArrayList<>();
        while (index < doctorsForResponse.size() && index < searchRequestBody.getIndexFrom()+10){
            result.add(doctorsForResponse.get(index));
            index++;
        }
        int count = doctorsForResponse.size();
        return new Responses.SearchResponseBody(result, count);
    }
}
