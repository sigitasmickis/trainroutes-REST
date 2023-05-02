package com.mickis.trainroutes.controller;

import com.mickis.trainroutes.io.TrainsDTO;
import com.mickis.trainroutes.repository.TrainRepository;
import com.mickis.trainroutes.repository.TrainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class TrainController {


    @Autowired
    TrainServices trainServices;

    @GetMapping("/trains")
    public ResponseEntity<TrainsDTO> trains() {
        return new ResponseEntity<>(new TrainsDTO(trainServices.getAllTrains()),
                HttpStatus.OK
        );
    }
}