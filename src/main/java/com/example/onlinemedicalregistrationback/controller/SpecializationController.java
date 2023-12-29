package com.example.onlinemedicalregistrationback.controller;

import com.example.onlinemedicalregistrationback.repository.SpecializationRepository;
import com.example.onlinemedicalregistrationback.serializableClasses.Responses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpecializationController {
    SpecializationRepository specializationRepository;

    public SpecializationController(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @PostMapping("/specializations")
    Responses.SpecializationsResponseBody allOrganizations(){
        return new Responses.SpecializationsResponseBody(specializationRepository.findAllNames());
    }
}
