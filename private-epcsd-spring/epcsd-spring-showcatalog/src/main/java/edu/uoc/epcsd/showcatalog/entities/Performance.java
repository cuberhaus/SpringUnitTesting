package edu.uoc.epcsd.showcatalog.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import javax.persistence.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class Performance {
    private Date date;
    private Timestamp time;
    private Integer remainingSeats;
}