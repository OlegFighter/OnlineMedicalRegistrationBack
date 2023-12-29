package com.example.onlinemedicalregistrationback.serializableClasses;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Requests {
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class SearchRequestBody implements Serializable {
        String name;
        String organization;
        String specialization;
        int indexFrom;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class AllDoctorsRequestBody implements Serializable {
        int indexFrom;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class CreateRecordRequestBody implements Serializable {
        long recordId;
        long doctorId;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class GetAvailableRecordsRequestBody implements Serializable {
        long doctorId;
        int indexFrom;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class VerifyRequestBody implements Serializable {
        String passport;
        String policy;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class SignInRequestBody implements Serializable {
        String policy;
        String password;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class SignUpRequestBody implements Serializable {
        String password;
        String policy;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class VerifyCancelRequestBody implements Serializable {
        LocalDateTime time;
    }
}
