package com.example.onlinemedicalregistrationback.controller;

import com.example.onlinemedicalregistrationback.repository.SpecializationRepository;
import com.example.onlinemedicalregistrationback.dto.Responses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SpecializationController {
    SpecializationRepository specializationRepository;

    @PostMapping("/specializations")
    Responses.SpecializationsResponseBody allOrganizations(){
        return new Responses.SpecializationsResponseBody(specializationRepository.findAllNames());
    }
}
