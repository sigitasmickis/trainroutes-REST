package com.mickis.trainroutes.controller;

import com.mickis.trainroutes.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
//@EnableWebMvc
public class GlobalExceptionAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String responseUniqueViolation(DataIntegrityViolationException ex) {
        if (ex.getMessage().contains("train_number")) {
            return "Unique train already exist, cannot be duplicated!";
        }
        else throw new RuntimeException("Unknown violation exception");
    }

    @ResponseBody
    @ExceptionHandler(TrainNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String trainNotFoundHandler(TrainNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(IllegalCityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String illegalCityExceptionHandler(IllegalCityException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UniqueTrainAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String uniqueTrainAlreadyExistExceptionHandler(UniqueTrainAlreadyExistException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(IllegalLocalTimeParsingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String illegalLocalTimeParsingExceptionHandler(IllegalLocalTimeParsingException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MissingRouteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String missingRouteExceptionHandler(MissingRouteException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UpdatingTrainDTOBodyAndTrainNumberMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String updatingTrainNumberAndDataContentMismatchExceptionHandler(UpdatingTrainDTOBodyAndTrainNumberMismatchException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String clientNotFoundExceptionHandler(ClientNotFoundException ex) {
        return ex.getMessage();
    }

}
