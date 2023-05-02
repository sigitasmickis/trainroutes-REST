package com.mickis.trainroutes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trains")
@Getter
@Setter
@ToString
//@EqualsAndHashCode(exclude = "city")
@RequiredArgsConstructor
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(name = "train_no", length = 15)
    private String trainNumber;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "citifrom_id", nullable = false)
    private City cityFrom;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cityto_id", nullable = false)
    private City cityTo;


    @NonNull
    @Column(name = "depart_time")
    private LocalTime departTime;

    @NonNull
    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @Column(name = "price_rate")
    private int priceRate;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ticket_id",nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private Set<Ticket> tickets = new HashSet<>();


    @Override
    public String toString() {
        return "Train{" +
                "trainNumber='" + trainNumber + '\'' +
                ", cityFrom=" + cityFrom.getName() +
                ", cityTo=" + cityTo.getName() +
                ", departTime=" + departTime +
                ", arrivalTime=" + arrivalTime +
                ", priceRate=" + priceRate +
                '}';
    }

    //    public Page<Train>
}
