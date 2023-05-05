package com.mickis.trainroutes.controller;

import com.mickis.trainroutes.io.TicketsDTO;
import com.mickis.trainroutes.repository.ClientTicketsServices;
import com.mickis.trainroutes.repository.TrainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1")
public class ClientTicketsController {



    @Autowired
    TrainServices trainServices;

    @Autowired
    ClientTicketsServices clientTicketsServices;


    @PostMapping("/users/{userId}/buy_tickets")
    public ResponseEntity<TicketsDTO> buyTicket(@PathVariable Long userId, @RequestParam String trainNo,
                                                @RequestParam(required = false) Integer quantity) {
        if (quantity == null) quantity = 1;
        return new ResponseEntity<>(new TicketsDTO(
                clientTicketsServices.buyNewTicketsDTO(trainNo, userId, quantity)),
                HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/get_tickets")
    public ResponseEntity<TicketsDTO> getTicket(@PathVariable Long userId){
        return new ResponseEntity<>(new TicketsDTO(
                clientTicketsServices.getUserTicketsDTO(userId)),
                HttpStatus.OK);
    }







}
