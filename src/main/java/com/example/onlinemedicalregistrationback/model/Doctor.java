package com.example.onlinemedicalregistrationback.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "doctor", schema = "public")
public class Doctor {
    @Id
    @GeneratedValue
    long id;
    String name;
    String organization;
    String specialization;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Record> records;

    public Doctor(String name, String organization, String specialization, Set<Record> records) {
        this.name = name;
        this.organization = organization;
        this.records = records;
        this.specialization = specialization;
    }

    public Doctor(long id, String name, String organization, String specialization, Set<Record> records) {
        this.id = id;
        this.name = name;
        this.organization = organization;
        this.specialization = specialization;
        this.records = records;
    }

    public Doctor(String name, String organization, String specialization) {
        this.name = name;
        this.organization = organization;
        this.specialization = specialization;
    }

    public Doctor() {

    }
}
