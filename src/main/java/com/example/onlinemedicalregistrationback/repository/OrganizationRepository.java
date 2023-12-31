package com.example.onlinemedicalregistrationback.repository;

import com.example.onlinemedicalregistrationback.model.Organization;
import com.example.onlinemedicalregistrationback.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, String> {
    @Query(value = "select name from Organization")
    List<String> findAllNames();
}
