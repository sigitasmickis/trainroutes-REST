package com.mickis.trainroutes.repository;

import com.mickis.trainroutes.entities.Ticket;
import org.springframework.stereotype.Service;

@Service
public class TicketServices {

    private ClientRepository clientRepository;

    private TrainRepository trainRepository;

    public TicketServices(ClientRepository clientRepository, TrainRepository trainRepository) {
        this.clientRepository = clientRepository;
        this.trainRepository = trainRepository;
    }

    public Ticket createNewTicket() {
        /*
        gauna arba esamą arba naują aptarnautiną klientą
        traukinio id pagal traukinio numerį (plius patikrinimas, ar toks yra)
        suformuoja ticket objektą, be nuolaidos (100%)
         */
        return null;
    }

}
