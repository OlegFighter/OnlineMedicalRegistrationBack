package com.example.onlinemedicalregistrationback.repository;

import com.example.onlinemedicalregistrationback.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
