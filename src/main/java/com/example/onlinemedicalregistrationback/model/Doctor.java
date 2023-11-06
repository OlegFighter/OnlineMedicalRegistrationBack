package com.example.onlinemedicalregistrationback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Doctor {
    @Id
    @GeneratedValue
    long id;
    String name;
    String organization;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Record> records;

    public Doctor(String name, String organization, Set<Record> records) {
        this.name = name;
        this.organization = organization;
        this.records = records;
    }

    public Doctor() {

    }
}
