package com.mickis.trainroutes.controller;

import com.mickis.trainroutes.errors.UniqueTrainAlreadyExistException;
import com.mickis.trainroutes.errors.UpdatingTrainDTOBodyAndNumberMismatchException;
import com.mickis.trainroutes.io.CitiesDTO;
import com.mickis.trainroutes.io.TrainDTO;
import com.mickis.trainroutes.io.TrainsDTO;
import com.mickis.trainroutes.repository.CityRepository;
import com.mickis.trainroutes.repository.TrainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("trains/{trainNo}")
    ResponseEntity<TrainDTO> one(@PathVariable String trainNo) {
        return new ResponseEntity<>(trainServices.getTrainByTrainNo(trainNo), HttpStatus.OK);
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
    public ResponseEntity<TrainDTO> newTrain(@RequestBody TrainDTO newTrainDTO) {
        if (trainServices.createNewTrainDTO(newTrainDTO)) {
            return new ResponseEntity<>(newTrainDTO, HttpStatus.OK);
        } else {
            throw new UniqueTrainAlreadyExistException(newTrainDTO.getTrainNumber());
        }
    }

    @PutMapping("/trains/{trainNo}")
    public ResponseEntity<TrainDTO> replaceTrain(@PathVariable String trainNo,
                                                 @RequestBody TrainDTO trainDTO) {
        if (trainDTO.getTrainNumber().equals(trainNo)) {
            if (trainServices.ifTrainIsNotPresent(trainDTO)) {
                trainServices.createNewTrainDTO(trainDTO);
                return new ResponseEntity<>(trainDTO, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(trainServices.updateTrain(trainDTO), HttpStatus.OK);
            }
        } else {
            throw new UpdatingTrainDTOBodyAndNumberMismatchException(trainNo, trainDTO);
        }
    }

    @DeleteMapping("/trains/{trainNo}")
    public ResponseEntity<String> deleteTrain(@PathVariable String trainNo) {
        trainServices.deleteTrain(trainNo);
        return new ResponseEntity<>(String.format("%s deleted!", trainNo), HttpStatus.OK);
    }


}