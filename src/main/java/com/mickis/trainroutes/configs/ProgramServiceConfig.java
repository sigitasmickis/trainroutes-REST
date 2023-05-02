package com.mickis.trainroutes.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mickis.trainroutes.repository.CityRepository;
import com.mickis.trainroutes.repository.TrainRepository;
import com.mickis.trainroutes.repository.TrainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProgramServiceConfig {


    @Autowired
    TrainRepository trainRepository;

    @Autowired
    CityRepository cityRepository;



//    @Bean
//    public TrainServices trainServices() {
//        return new TrainServices(cityRepository, trainRepository);
//    }


}
