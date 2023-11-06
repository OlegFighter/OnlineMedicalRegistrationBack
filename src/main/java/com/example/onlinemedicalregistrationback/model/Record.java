package com.example.onlinemedicalregistrationback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Record {
    @Id
    @GeneratedValue
    long id;
    LocalDateTime time;

    public Record(LocalDateTime time) {
        this.time = time;
    }
}