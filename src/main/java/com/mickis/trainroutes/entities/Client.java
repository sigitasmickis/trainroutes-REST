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


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column
    private long userId;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();

    @Override
    public String toString() {
        return "client " + userId +
                " tickets: " + tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }


}
