package com.mickis.trainroutes.controller;

import com.mickis.trainroutes.io.TicketDTO;
import com.mickis.trainroutes.io.TicketsDTO;
import com.mickis.trainroutes.io.TrainsDTO;
import com.mickis.trainroutes.repository.ClientTicketsServices;
import com.mickis.trainroutes.repository.TrainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;

@RestController
public class ClientTicketsController {



    @Autowired
    TrainServices trainServices;

    @Autowired
    ClientTicketsServices clientTicketsServices;


    @PostMapping("/users/{userId}/get_ticket")
    public ResponseEntity<TicketsDTO> getTicket(@PathVariable Long userId, @RequestParam String trainNo,
                                                @RequestParam(required = false) Integer quantity) {
        if (quantity == null) quantity = 1;
        return new ResponseEntity<>(new TicketsDTO(
                clientTicketsServices.getNewTicketsDTO(trainNo, userId, quantity)),
                HttpStatus.OK);
    }




}
