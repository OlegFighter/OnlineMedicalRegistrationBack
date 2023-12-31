package com.example.onlinemedicalregistrationback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CreateRecordRequestBody implements Serializable {
    long recordId;
    long doctorId;
}
