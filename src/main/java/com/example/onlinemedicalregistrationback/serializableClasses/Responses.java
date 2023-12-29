package com.example.onlinemedicalregistrationback.serializableClasses;

import com.example.onlinemedicalregistrationback.notDBModel.DoctorView;
import com.example.onlinemedicalregistrationback.notDBModel.RecordView;
import lombok.*;
import com.example.onlinemedicalregistrationback.model.Record;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Responses {
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class GetAvailableRecordsResponseBody implements Serializable{
        ArrayList<RecordView> availableRecords;
        String name;
        String specialization;
        String organization;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class CreateRecordResponseBody implements Serializable{
        String result;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class SearchResponseBody implements Serializable {
        ArrayList<DoctorView> doctors;
        int count;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class AllDoctorResponseBody implements Serializable {
        ArrayList<DoctorView> doctors;
        int count;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class DoctorsByOrgResponseBody implements Serializable {
        ArrayList<DoctorView> doctors;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class VerifyResponseBody implements Serializable {
        String answer;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class SignInResponseBody implements Serializable {
        String answer;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class OrganizationsResponseBody implements Serializable {
        List<String> organizations;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class SpecializationsResponseBody implements Serializable {
        List<String> specializations;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class SignUpResponseBody implements Serializable {
        String policy;
    }


}
