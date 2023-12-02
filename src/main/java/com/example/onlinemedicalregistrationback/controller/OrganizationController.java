package com.example.onlinemedicalregistrationback.controller;

import com.example.onlinemedicalregistrationback.repository.OrganizationRepository;
import com.example.onlinemedicalregistrationback.serializableClasses.Responses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganizationController {
    OrganizationRepository organizationRepository;

    public OrganizationController(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @PostMapping("/organizations")
    Responses.OrganizationsResponseBody allOrganizations(){
        return new Responses.OrganizationsResponseBody(organizationRepository.findAllNames());
    }
}
