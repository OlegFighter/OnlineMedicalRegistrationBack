package com.example.onlinemedicalregistrationback.controller;

import com.example.onlinemedicalregistrationback.repository.OrganizationRepository;
import com.example.onlinemedicalregistrationback.dto.Responses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrganizationController {
    OrganizationRepository organizationRepository;

    @PostMapping("/organizations")
    Responses.OrganizationsResponseBody allOrganizations(){
        return new Responses.OrganizationsResponseBody(organizationRepository.findAllNames());
    }
}
