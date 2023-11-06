package com.example.onlinemedicalregistrationback.serializableClasses;

import lombok.*;
import com.example.onlinemedicalregistrationback.model.Record;

import java.io.Serializable;
import java.util.Set;

public class Responses {
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class GetAvailableRecordsResponseBody implements Serializable{
        Set<Record> availableRecords;
    }
}
