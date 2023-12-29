package com.example.onlinemedicalregistrationback.repository;

import com.example.onlinemedicalregistrationback.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
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

    @Query(value = "select min(record.time)::timestamp::date from record inner join doctor_records on record.id = doctor_records.records_id where doctor_id =:doctorId",
            nativeQuery = true)
    LocalDateTime nearestDate(long doctorId);

    //@Query(value = "select * from record inner join doctor_records on record.id = doctor_records.records_id where doctor_id =:doctorId and time::timestamp::date =:time", nativeQuery = true)
    //ArrayList<Record> findByDate(long doctorId, LocalDateTime time);

    @Query(value = "select r.* from record r " +
            "inner join doctor_records dr on r.id = dr.records_id where dr.doctor_id =:doctorId and " +
            "date(r.time) = (select min(date(r.time)) + interval '1 day'*:page from record r " +
            "inner join doctor_records dr on r.id = dr.records_id where dr.doctor_id =:doctorId)",nativeQuery = true)
    ArrayList<Record> findByDate(@Param("doctorId") long doctorId, @Param("page") int page);

}
