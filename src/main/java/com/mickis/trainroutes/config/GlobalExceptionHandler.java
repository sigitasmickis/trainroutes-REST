package com.mickis.trainroutes.config;

import com.mickis.trainroutes.entities.Train;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@EnableWebMvc
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> responseUniqueViolation(Exception ex) {
        if (ex.getMessage().contains("train_number")) {
            logger.error("error message: {}",ex.getCause().getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("train already exist");
        }
        else throw new RuntimeException("unknown violation exception");
    }

}
