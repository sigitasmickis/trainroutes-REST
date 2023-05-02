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

    public Train createNewTrain(String trainNumber, String destCity, int priceRate) {
        var cityVilnius = cityRepository.save(new City("Vilnius"));
        var cityKaunas = cityRepository.save(new City(destCity));
        var departTime = LocalTime.of(19, 15);
        var arriveTime = LocalTime.of(20, 30);
        var train = new Train(trainNumber, cityVilnius, cityKaunas,
                departTime, arriveTime);
        train.setPriceRate(priceRate);
        return trainRepository.save(train);
    }

    private Client getNewClient(long l) {
        var client = new Client(l);
        var persistedClient = clientRepository.save(client);
        return persistedClient;
    }

    private Ticket getNewTicket(Train train1, Client client, int discount) {
        var ticket = new Ticket(train1, train1.getPriceRate() * discount / 100);
        ticket.setClient(client);
        return ticketRepository.save(ticket);
    }

    @Test
    public void givenOneTrain_whenTicketIsBought_TicketIsAddedToClient() {
        var train1 = createNewTrain("Tr-065","Kaunas", 10000);
        var persistedClient1 = getNewClient(2L);

        var persistedTicket = getNewTicket(train1, persistedClient1, 70);
        var foundTicket = entityManager.find(Ticket.class, persistedTicket.getId());
        assertEquals("Kaunas", foundTicket.getTrainId().getCityTo().getName());
        assertEquals(7000, foundTicket.getUserPrice());
    }

    @Test
    public void givenOneTrain_whenTwoTicketAreBought_TicketsAreAddedToClients() {
        var persistedTrain1 = createNewTrain("Tr-065","Kaunas", 10000);
        var persistedClient1 = getNewClient(1L);
        var persistedTicket1 = getNewTicket(persistedTrain1, persistedClient1, 70);
        persistedClient1.addTicket(persistedTicket1);
        var foundClient1 = entityManager.find(Client.class, persistedClient1.getId());
        System.out.println(foundClient1);
        var persistedClient2 = getNewClient(2L);
        var persistedTicket2 = getNewTicket(persistedTrain1, persistedClient2, 80);
        persistedClient2.addTicket(persistedTicket2);
        var foundClient2 = entityManager.find(Client.class, persistedClient2.getId());
        System.out.println(foundClient2);
    }

    @Test
    public void givenTwoTrains_whenForEachOneTicketisBought_TicketsAreAddedToClients() {
        var persistedTrain1 = createNewTrain("Tr-065","Kaunas", 10000);
        var persistedTrain2 = createNewTrain("Tr-089","Klaipėda", 15000);
        var persistedClient1 = getNewClient(1L);
        var persistedTicket1 = getNewTicket(persistedTrain1, persistedClient1, 70);
        persistedClient1.addTicket(persistedTicket1);
        var foundClient1 = entityManager.find(Client.class, persistedClient1.getId());
        System.out.println(foundClient1);
        var persistedClient2 = getNewClient(2L);
        var persistedTicket2 = getNewTicket(persistedTrain2, persistedClient2, 80);
        persistedClient2.addTicket(persistedTicket2);
        var foundClient2 = entityManager.find(Client.class, persistedClient2.getId());
        System.out.println(foundClient2);
    }

    @Test
    public void givenOneTrain_whenTwoTicketsAreBought_OnlyOneTicketIsAddedToClients() {
        var persistedTrain1 = createNewTrain("Tr-065","Kaunas", 10000);
        var persistedClient1 = getNewClient(1L);
        var persistedTicket1 = getNewTicket(persistedTrain1, persistedClient1, 70);
        var persistedTicket2 = getNewTicket(persistedTrain1, persistedClient1, 80);
        persistedClient1.addTicket(persistedTicket1);
        persistedClient1.addTicket(persistedTicket2);
        var foundClient1 = entityManager.find(Client.class, persistedClient1.getId());
        System.out.println(foundClient1);
    }


}