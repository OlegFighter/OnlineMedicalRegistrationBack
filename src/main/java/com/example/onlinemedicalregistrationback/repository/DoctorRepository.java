package com.example.onlinemedicalregistrationback.repository;

import com.example.onlinemedicalregistrationback.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query(value = "select d from Doctor d")
    List<Doctor> findAllD();

    @Query(value = "select d from Doctor d where d.name like %:reqName%")
    List<Doctor> findByName(@Param("reqName") String reqName);

    @Query(value = "select d from Doctor d where d.organization like %:orgName%")
    List<Doctor> findByOrganization(@Param("orgName") String orgName);

    @Query(value = "select d from Doctor d where d.specialization like %:specName%")
    List<Doctor> findBySpecialization(@Param("specName") String specName);

    @Query(value = "select d from Doctor d where d.name like %:reqName% and d.organization like %:orgName%")
    List<Doctor> findByNameAndOrganization(@Param("reqName") String reqName, @Param("orgName") String orgName);

    @Query(value = "select d from Doctor d where d.name like %:reqName% and d.specialization like %:specName%")
    List<Doctor> findByNameAndSpecialization(@Param("reqName") String reqName, @Param("specName") String specName);

    @Query(value = "select d from Doctor d where d.specialization like %:specName% and d.organization like %:orgName%")
    List<Doctor> findByOrganizationAndSpecialization(@Param("specName") String specName, @Param("orgName") String orgName);

    @Query(value = "select d from Doctor d where d.name like %:reqName% and d.organization like %:orgName% and d.specialization like %:specName%")
    List<Doctor> findByNameAndOrganizationAndSpecialization(@Param("reqName") String reqName, @Param("orgName") String orgName, @Param("specName") String specName);
}
