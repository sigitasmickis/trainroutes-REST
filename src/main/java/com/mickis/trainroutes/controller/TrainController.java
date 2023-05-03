package com.mickis.trainroutes.controller;

import com.mickis.trainroutes.entities.Train;
import com.mickis.trainroutes.io.CitiesDTO;
import com.mickis.trainroutes.io.TrainsDTO;
import com.mickis.trainroutes.repository.CityRepository;
import com.mickis.trainroutes.repository.TrainRepository;
import com.mickis.trainroutes.repository.TrainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class TrainController {


    @Autowired
    TrainServices trainServices;

    @Autowired
    CityRepository cityRepository;

    @GetMapping("/trains")
    public ResponseEntity<TrainsDTO> trains() {
        return new ResponseEntity<>(new TrainsDTO(trainServices.getAllTrains()),
                HttpStatus.OK
        );
    }

    @GetMapping("/trains/cities")
    public ResponseEntity<CitiesDTO> cities() {
        return new ResponseEntity<>(new CitiesDTO(cityRepository.findAll()),
                HttpStatus.OK );
    }

    @GetMapping("/trains/route")
    public ResponseEntity<TrainsDTO> trainsRoute(@RequestParam String from, @RequestParam String to) {
        return new ResponseEntity<>(new TrainsDTO(trainServices.getRouteFromCityToCity(from, to)),
                HttpStatus.OK );
    }


}