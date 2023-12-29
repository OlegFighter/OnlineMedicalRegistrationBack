package com.example.onlinemedicalregistrationback.notDBModel;

import com.example.onlinemedicalregistrationback.model.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Comparator;

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
        /*this.id = record.getId();
        String inserting = new String();
        if(record.getTime().getMonth().getValue() < 10){
            inserting = "0" + record.getTime().getMonth().getValue();
        }else {
            inserting = String.valueOf(record.getTime().getMonth().getValue());
        }
        this.time = record.getTime().getDayOfMonth() + "." + inserting + "." + record.getTime().getYear() + ", " + record.getTime().getHour() + ":" + record.getTime().getMinute();*/
        this.time = record.getTime().toString();
    }

    public static final Comparator<RecordView> COMPARE_BY_TIME = new Comparator<RecordView>() {
        @Override
        public int compare(RecordView o1, RecordView o2) {
            return o1.getTime().compareTo(o2.getTime());
        }
    };
}
