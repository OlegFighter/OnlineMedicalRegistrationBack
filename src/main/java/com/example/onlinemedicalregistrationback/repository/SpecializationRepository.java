package com.example.onlinemedicalregistrationback.repository;

import com.example.onlinemedicalregistrationback.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface SpecializationRepository extends JpaRepository<Organization, String> {
    @Query(value = "select name from Specialization")
    ArrayList<String> findAllNames();
}
