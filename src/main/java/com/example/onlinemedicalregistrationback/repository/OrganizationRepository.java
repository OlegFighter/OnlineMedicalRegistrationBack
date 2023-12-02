package com.example.onlinemedicalregistrationback.repository;

import com.example.onlinemedicalregistrationback.model.Organization;
import com.example.onlinemedicalregistrationback.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface OrganizationRepository extends JpaRepository<Organization, String> {
    @Query(value = "select name from Organization")
    ArrayList<String> findAllNames();
}
