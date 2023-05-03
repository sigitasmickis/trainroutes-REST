package com.mickis.trainroutes.controller;

import com.mickis.trainroutes.entities.Train;
import com.mickis.trainroutes.io.CitiesDTO;
import com.mickis.trainroutes.io.TrainDTO;
import com.mickis.trainroutes.io.TrainsDTO;
import com.mickis.trainroutes.repository.CityRepository;
import com.mickis.trainroutes.repository.TrainRepository;
import com.mickis.trainroutes.repository.TrainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
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
                HttpStatus.OK);
    }

    @GetMapping("/trains/route")
    public ResponseEntity<TrainsDTO> trainsRoute(@RequestParam String from, @RequestParam String to) {
        return new ResponseEntity<>(new TrainsDTO(trainServices.getRouteFromCityToCity(from, to)),
                HttpStatus.OK);
    }

    @PostMapping("/trains")
    public ResponseEntity<TrainDTO> newTrain(@RequestBody TrainDTO newTrain) {
        if (trainServices.createNewTrainDTO(newTrain)) {
            return new ResponseEntity<>(newTrain,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(newTrain, HttpStatus.NOT_MODIFIED);
        }
    }


}