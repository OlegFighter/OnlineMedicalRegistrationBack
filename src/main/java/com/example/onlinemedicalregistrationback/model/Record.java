package com.example.onlinemedicalregistrationback.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Comparator;

@Getter
@Setter
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "record_seq")
    long id;
    LocalDateTime time;

    public Record(LocalDateTime time) {
        this.time = time;
    }

    public Record() {

    }

    public static final Comparator<Record> COMPARE_BY_TIME = Comparator.comparing(Record::getTime);
}