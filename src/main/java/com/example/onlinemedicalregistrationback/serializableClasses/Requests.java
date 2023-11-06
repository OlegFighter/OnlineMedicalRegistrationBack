package com.example.onlinemedicalregistrationback.serializableClasses;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

public class Requests {
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class GetAvailableRecordsRequestBody implements Serializable {
        long doctorId;
    }
}
