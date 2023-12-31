package com.example.onlinemedicalregistrationback.dto;

import com.example.onlinemedicalregistrationback.model.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordView {
    @Id
    @GeneratedValue
    long id;
    String time;

    public RecordView(String time) {
        this.time = time;
    }

    public RecordView(Record record) {
        this.id = record.getId();
        this.time = record.getTime()
            .format(DateTimeFormatter
                .ofPattern("Время ss:mm:hh, Дата d MMMM uuuu", Locale.of("ru-RU")));
    }

    public static final Comparator<RecordView> COMPARE_BY_TIME =
        Comparator.comparing(RecordView::getTime);
}
