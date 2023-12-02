package com.example.onlinemedicalregistrationback.repository;

import com.example.onlinemedicalregistrationback.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface RecordRepository extends JpaRepository<Record, Long> {
    @Query(value = "select * from record inner join doctor_records on record.id = doctor_records.records_id where doctor_id =:doctorId",
            nativeQuery = true
    )
    ArrayList<Record> findByDoctor(long doctorId);

    @Query(value = "DELETE FROM doctor_records WHERE records_id =:recordId; DELETE FROM record WHERE id =:recordId",
            nativeQuery = true
    )
    void deleteRecord(long recordId);
}
