package com.mickis.trainroutes;

import com.mickis.trainroutes.entities.City;
import com.mickis.trainroutes.io.TrainDTO;
import com.mickis.trainroutes.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainroutesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TrainroutesApplication.class, args);
    }

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private TrainServices trainServices;


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void run(String... args) throws Exception {
        initDao();

    }

    private void initDao() {
//		this.trainServices = new TrainServices(cityRepository, trainRepository);
//		initCities();
//		initTrains();
    }

    private void initTrains() {
        trainServices.createNewTrainDTO(new TrainDTO("Tr-065",
                "Vilnius", "Kaunas", "09:15", "10:20"));
        trainServices.createNewTrainDTO(new TrainDTO("Tr-066",
                "Kaunas", "Vilnius", "11:23", "12:45"));
        trainServices.createNewTrainDTO(new TrainDTO("Tr-082",
                "Vilnius", "Trakai", "08:10", "09:00"));
        trainServices.createNewTrainDTO(new TrainDTO("Tr-083",
                "Trakai", "Vilnius", "10:30", "11:20"));
        trainServices.createNewTrainDTO(new TrainDTO("Tr-055",
                "Vilnius", "Klaipėda", "06:40", "11:05"));
        System.out.println(trainServices.createNewTrainDTO(new TrainDTO("Tr-056",
                "Klaipėda", "Vilnius", "16:30", "20:35")));


    }

    private void initCities() {
        cityRepository.save(new City("Vilnius"));
        cityRepository.save(new City("Kaunas"));
        cityRepository.save(new City("Klaipėda"));
        cityRepository.save(new City("Alytus"));
        cityRepository.save(new City("Trakai"));
        cityRepository.save(new City("Varėna"));
        cityRepository.save(new City("Turmantas"));

    }
}
