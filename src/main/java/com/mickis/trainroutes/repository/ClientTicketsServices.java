package com.mickis.trainroutes.repository;

import com.mickis.trainroutes.entities.Client;
import com.mickis.trainroutes.entities.Ticket;
import com.mickis.trainroutes.entities.Train;
import com.mickis.trainroutes.io.TicketDTO;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientTicketsServices {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");

    private TrainServices trainServices;

    private ClientRepository clientRepository;

    private TicketRepository ticketRepository;

    public ClientTicketsServices(TrainServices trainServices,
                                 ClientRepository clientRepository,
                                 TicketRepository ticketRepository) {
        this.trainServices = trainServices;
        this.clientRepository = clientRepository;
        this.ticketRepository = ticketRepository;
    }


    public List<TicketDTO> getNewTicketsDTO(String trainNo, long userId, int quantity) {
        List<Ticket> ticketList = persistNewTicket(trainNo, userId, quantity);
        return ticketList.stream()
                .map(ticket -> getTicketDTOFromDb(ticket))
                .collect(Collectors.toList());
    }

    public List<Ticket> persistNewTicket(String trainNo, long userId, int quantity) {
        Train train = trainServices.getTrainByTrainNo(trainNo);
        var client = clientRepository.findByUserId(userId)
                .orElse(null);
        if (client == null) client = clientRepository.save(new Client(userId, 100));
        List<Ticket> ticketList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            var ticket = new Ticket(train, train.getPriceRate() * client.getDiscount() / 100);
            ticket.setClient(client);
            ticketRepository.save(ticket);
            client.addTicket(ticket);
            ticketList.add(ticket);
        }
        return ticketList;
    }

    public TicketDTO getTicketDTOFromDb(Ticket ticket) {
        var ticketDTO = new TicketDTO();
        ticketDTO.setTrainNumber(ticket.getTrainId().getTrainNumber());
        ticketDTO.setUserId(ticket.getClient().getUserId());
        ticketDTO.setUserPrice(ticket.getUserPrice());
        ticketDTO.setCityFrom(ticket.getTrainId().getCityFrom().getName());
        ticketDTO.setCityTo(ticket.getTrainId().getCityTo().getName());
        ticketDTO.setDepartTime(ticket.getTrainId().getDepartTime().format(format));
        ticketDTO.setArrivalTime(ticket.getTrainId().getArrivalTime().format(format));
        return ticketDTO;
    }
}
