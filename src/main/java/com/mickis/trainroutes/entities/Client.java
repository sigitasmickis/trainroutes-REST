package com.mickis.trainroutes.entities;

import com.mickis.trainroutes.validation.safeField.SafeField;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Client {


    public Client(long userId, int discout) {
        this.userId = userId;
        this.discount = discout;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private long userId;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();

    private int discount;

    @Override
    public String toString() {
        return "client " + userId +
                " tickets: " + tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }


}
