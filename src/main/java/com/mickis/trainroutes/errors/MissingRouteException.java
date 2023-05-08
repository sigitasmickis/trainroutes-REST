package com.mickis.trainroutes.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class MissingRouteException extends RuntimeException{

   public MissingRouteException(String from, String to) {
      super(String.format("%s - %s", from, to));
   }
}
