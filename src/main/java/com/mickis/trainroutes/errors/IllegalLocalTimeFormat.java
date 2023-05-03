package com.mickis.trainroutes.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParseException;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class IllegalLocalTimeFormat extends RuntimeException {

}
