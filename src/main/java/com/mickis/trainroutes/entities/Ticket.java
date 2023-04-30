package com.mickis.trainroutes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@ToString
//@EqualsAndHashCode(exclude = "train, client")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @JoinColumn(name = "train_id", nullable = false)
    private Train trainId;

    @NonNull
    private int userPrice;


//    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ticket_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Client client;

    @Override
    public String toString() {
        return "trainNumber=" + trainId.getTrainNumber() +
                ", fromCity=" + trainId.getCityFrom().getName() +
                ", toCity=" + trainId.getCityTo().getName() +
                ", departTime" + trainId.getDepartTime() +
                ", clientPrice=" + userPrice +
                '}';
    }
}
