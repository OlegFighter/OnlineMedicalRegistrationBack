package com.example.onlinemedicalregistrationback.model;

import javax.persistence.*;

import com.example.onlinemedicalregistrationback.notDBModel.DoctorView;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Comparator;

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

    public Record() {

    }

    public static final Comparator<Record> COMPARE_BY_TIME = new Comparator<Record>() {
        @Override
        public int compare(Record o1, Record o2) {
            return o1.getTime().compareTo(o2.getTime());
        }
    };
}