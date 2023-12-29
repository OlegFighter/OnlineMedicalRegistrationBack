package com.example.onlinemedicalregistrationback.notDBModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorView {
    long id;
    String name;
    String organization;
    String specialization;

    public DoctorView(String name, String organization, String specialization) {
        this.name = name;
        this.organization = organization;
        this.specialization = specialization;
    }

    public static final Comparator<DoctorView> COMPARE_BY_NAME = new Comparator<DoctorView>() {
        @Override
        public int compare(DoctorView o1, DoctorView o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };
}
