package com.example.onlinemedicalregistrationback.repository;

import com.example.onlinemedicalregistrationback.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    @Query(value = "select * from record " +
        "inner join doctor_records on record.id = doctor_records.records_id " +
        "where doctor_id =:doctorId",
            nativeQuery = true
    )
    List<Record> findByDoctor(long doctorId);

    @Modifying
    @Query(value = "DELETE FROM doctor_records WHERE records_id =:recordId; " +
        "DELETE FROM record WHERE id =:recordId",
            nativeQuery = true
    )
    void deleteRecord(long recordId);

    @Query(value = "select min(record.time)::timestamp::date from record " +
        "inner join doctor_records on record.id = doctor_records.records_id " +
        "where doctor_id =:doctorId",
            nativeQuery = true)
    LocalDateTime nearestDate(long doctorId);

    //@Query(value = "select * from record inner join doctor_records on record.id = doctor_records.records_id where doctor_id =:doctorId and time::timestamp::date =:time", nativeQuery = true)
    //ArrayList<Record> findByDate(long doctorId, LocalDateTime time);


    /**
     *
     * @param doctorId
     * @param page is offset in days
     * @return
     */
    @Query(value = "" +
        "select r.*\n" +
        "from record r\n" +
        "         inner join doctor_records dr on r.id = dr.records_id\n" +
        "where dr.doctor_id = :doctorId\n" +
        "  and date(r.time) = (select " +
        "                           min(date(r.time)) + interval '1 day' * :page\n" +
        "                      from record r\n" +
        "                               inner join doctor_records dr on r.id = dr.records_id\n" +
        "                      where dr.doctor_id = :doctorId)",nativeQuery = true)
    List<Record> findByDate(@Param("doctorId") long doctorId, @Param("page") int page);

}
