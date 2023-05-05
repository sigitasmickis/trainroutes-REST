package com.mickis.trainroutes.repository;

import com.mickis.trainroutes.entities.City;
import com.mickis.trainroutes.entities.Client;
import com.mickis.trainroutes.entities.Ticket;
import com.mickis.trainroutes.entities.Train;
import com.mickis.trainroutes.io.TicketDTO;
import org.junit.jupiter.api.BeforeEach;
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
class ClientTicketsServicesTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TicketRepository ticketRepository;

    private TrainServices trainServices;

    private ClientTicketsServices clientTicketsServices;

    @BeforeEach
    public void startup() {
        this.trainServices = new TrainServices(cityRepository, trainRepository);
        this.clientTicketsServices = new ClientTicketsServices(
                trainServices,
                clientRepository,
                ticketRepository);
    }

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
        client.setDiscount(90);
        var persistedClient = clientRepository.save(client);
        return persistedClient;
    }

    private Ticket getNewTicket(Train train1, Client client, int discount) {
        var ticket = new Ticket(train1, train1.getPriceRate() * discount / 100);
        ticket.setClient(client);
        return ticketRepository.save(ticket);
    }

   @Test
    public void givenTicket_ticketDTOreturned_isCorrect() {
       var persistedTrain = createNewTrain("Tr-065","Kaunas", 10000);
       var persistedClient = getNewClient(2L);
       var persistedTicket = getNewTicket(persistedTrain,
               persistedClient,persistedClient.getDiscount());
       var createdDTO = clientTicketsServices.getTicketDTOFromDb(persistedTicket);
       var outputDTO = new TicketDTO(2L,"Tr-065",
               "Vilnius",
               "Kaunas","19:15","20:30", 9000);
       assertEquals(outputDTO, createdDTO);
   }



}