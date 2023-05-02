package com.mickis.trainroutes;

import com.mickis.trainroutes.entities.City;
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
		this.trainServices = new TrainServices(cityRepository);
//		initCities();
		initTrains();
	}

	private void initTrains() {


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
