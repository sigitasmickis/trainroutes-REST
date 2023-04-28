package com.mickis.trainroutes.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "trains")
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "city")
@RequiredArgsConstructor
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(name = "train_no")
    private String trainNumber;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "citifrom_id", nullable = false)
    private City cityFrom;


    @NonNull
    @Column(name = "depart_time")
    private LocalTime departTime;

    @NonNull
    @Column(name = "arrival_time")
    private LocalTime arrivalTime;
}
