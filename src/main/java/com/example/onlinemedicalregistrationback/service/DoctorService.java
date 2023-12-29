package com.example.onlinemedicalregistrationback.service;

import com.example.onlinemedicalregistrationback.model.Doctor;
import com.example.onlinemedicalregistrationback.model.Record;
import com.example.onlinemedicalregistrationback.notDBModel.DoctorView;
import com.example.onlinemedicalregistrationback.notDBModel.RecordView;
import com.example.onlinemedicalregistrationback.notFoundExceptions.DoctorNotFoundException;
import com.example.onlinemedicalregistrationback.repository.DoctorRepository;
import com.example.onlinemedicalregistrationback.repository.RecordRepository;
import com.example.onlinemedicalregistrationback.serializableClasses.Requests;
import com.example.onlinemedicalregistrationback.serializableClasses.Responses;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final RecordRepository recordRepository;

    public DoctorService(DoctorRepository doctorRepository, RecordRepository recordRepository) {
        this.doctorRepository = doctorRepository;
        this.recordRepository = recordRepository;
    }

    public Responses.GetAvailableRecordsResponseBody getRecords(Requests.GetAvailableRecordsRequestBody
                                                                        getAvailableRecordsRequestBody){
        ArrayList<Record> records = recordRepository.findByDate(getAvailableRecordsRequestBody.getDoctorId(), getAvailableRecordsRequestBody.getIndexFrom());
        records.sort(Record.COMPARE_BY_TIME);
        Doctor doctor = doctorRepository.findById(getAvailableRecordsRequestBody.getDoctorId())
                .orElseThrow(() -> new DoctorNotFoundException(getAvailableRecordsRequestBody.getDoctorId()));
        ArrayList<RecordView> recordViews = new ArrayList<>();
        for (Record record : records) {
            recordViews.add(new RecordView(record));
        }
        return new Responses.GetAvailableRecordsResponseBody(recordViews, doctor.getName(), doctor.getSpecialization(),
                doctor.getOrganization());
    }

    public Responses.AllDoctorResponseBody allDoctors(Requests.AllDoctorsRequestBody allDoctorsRequestBody){
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

    public Responses.SearchResponseBody search(Requests.SearchRequestBody searchRequestBody){
        final String flag = "EMPTY_VALUE";
        ArrayList<Doctor> doctors = new ArrayList<>();
        if (!Objects.equals(searchRequestBody.getName(), flag)
                && !Objects.equals(searchRequestBody.getOrganization(), flag)
                && !Objects.equals(searchRequestBody.getSpecialization(), flag)){
            doctors = doctorRepository.findByNameAndOrganizationAndSpecialization
                    (searchRequestBody.getName(), searchRequestBody.getOrganization(), searchRequestBody.getSpecialization());
        }
        else if (!Objects.equals(searchRequestBody.getName(), flag)
                && !Objects.equals(searchRequestBody.getOrganization(), flag)
                && Objects.equals(searchRequestBody.getSpecialization(), flag)){
            doctors = doctorRepository.findByNameAndOrganization
                    (searchRequestBody.getName(), searchRequestBody.getOrganization());
        }
        else if (!Objects.equals(searchRequestBody.getName(), flag)
                && Objects.equals(searchRequestBody.getOrganization(), flag)
                && !Objects.equals(searchRequestBody.getSpecialization(), flag)){
            doctors = doctorRepository.findByNameAndSpecialization
                    (searchRequestBody.getName(), searchRequestBody.getSpecialization());
        }
        else if (Objects.equals(searchRequestBody.getName(), flag)
                && !Objects.equals(searchRequestBody.getOrganization(), flag)
                && !Objects.equals(searchRequestBody.getSpecialization(), flag)){
            doctors = doctorRepository.findByOrganizationAndSpecialization
                    (searchRequestBody.getSpecialization(), searchRequestBody.getOrganization());
        }
        else if (!Objects.equals(searchRequestBody.getName(), flag)
                && Objects.equals(searchRequestBody.getOrganization(), flag)
                && Objects.equals(searchRequestBody.getSpecialization(), flag)){
            doctors = doctorRepository.findByName(searchRequestBody.getName());
        }
        else if (Objects.equals(searchRequestBody.getName(), flag)
                && Objects.equals(searchRequestBody.getOrganization(), flag)
                && !Objects.equals(searchRequestBody.getSpecialization(), flag)){
            doctors = doctorRepository.findBySpecialization(searchRequestBody.getSpecialization());
        }
        else if (Objects.equals(searchRequestBody.getName(), flag)
                && !Objects.equals(searchRequestBody.getOrganization(), flag)
                && Objects.equals(searchRequestBody.getSpecialization(), flag)){
            doctors = doctorRepository.findByOrganization(searchRequestBody.getOrganization());
        }
        else if (Objects.equals(searchRequestBody.getName(), flag)
                && Objects.equals(searchRequestBody.getOrganization(), flag)
                && Objects.equals(searchRequestBody.getSpecialization(), flag)){
            doctors = doctorRepository.findAllD();
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
