package com.mickis.trainroutes.entities;

import com.mickis.trainroutes.repository.CityRepository;
import com.mickis.trainroutes.repository.ClientRepository;
import com.mickis.trainroutes.repository.TicketRepository;
import com.mickis.trainroutes.repository.TrainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TicketTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public Train createNewTrain(String destCity, int priceRate) {
        var cityVilnius = cityRepository.save(new City("Vilnius"));
        var cityKaunas = cityRepository.save(new City(destCity));
        var departTime = LocalTime.of(19, 15);
        var arriveTime = LocalTime.of(20, 30);
        var train = new Train("Tr-065", cityVilnius, cityKaunas,
                departTime, arriveTime);
        train.setPriceRate(priceRate);
        return trainRepository.save(train);
    }

    private Client getClient(long l) {
        var client = new Client(l);
        var persistedClient = clientRepository.save(client);
        return persistedClient;
    }

    private Ticket getTicket(Train train1, Client client) {
        var ticket = new Ticket(train1, train1.getPriceRate()*7/10);
        return ticketRepository.save(ticket);
    }

    @Test
    public void givenOneTrain_whenTicketIsBought_TicketIsCreated() {
        var train1 = createNewTrain("Kaunas", 10000);
        var persistedClient1 = getClient(2L);

        var persistedTicket = getTicket(train1, persistedClient1);
        var foundTicket = entityManager.find(Ticket.class, persistedTicket.getId());
        assertEquals("Kaunas", foundTicket.getTrainId().getCityTo().getName());
        assertEquals(7000, foundTicket.getUserPrice());
    }

    @Test
    public void givenOneTrain_whenTicketIsBought_TicketIsAddedToClient() {
        var train1 = createNewTrain("Kaunas", 10000);
        var train2 = createNewTrain("Klaipėda", 15000);
        var client1 = new Client(2L);
        var client2 = new Client(3L);
        var persistedClient1 = clientRepository.save(client1);
        var persistedClient2 = clientRepository.save(client2);
        var ticket1 = new Ticket(train1, train1.getPriceRate()*7/10);
        ticket1.setClient(persistedClient1);
        var persistedTicket1 = ticketRepository.save(ticket1);
//        persistedClient1.addTicket(persistedTicket1);
//        var ticket2 = new Ticket(train1, train2.getPriceRate()*8/10, persistedClient1);
//        ticket2.setClient(persistedClient1);
//        var persistedTicket2 = ticketRepository.save(ticket2);

//        client1.addTicket(persistedTicket2);
//        var updatedClient = clientRepository.save(client1);
//        var foundClient = clientRepository.findByUserId(2L).get();
//        System.out.println(foundClient);
////        client1.addTicket(persistedTicket2);
//        System.out.println(foundClient);
//        assertEquals(train1, foundClient.getTickets().);
    }

    @Test
    public void givenOneTrain_whenTwoTicketsAreBoughtOnOneTrain_theyAreCreated() {
        var train1 = createNewTrain("Kaunas", 10000);
        var persistedClient1 = getClient(2L);
        var persistedTicket1 = getTicket(train1, persistedClient1);
//        var persistedTicket2 = getTicket(train1, persistedClient1);
        var foundTicket = entityManager.find(Ticket.class, persistedTicket1.getId());
//        assertEquals(, );

    }




}