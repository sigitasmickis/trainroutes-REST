package com.mickis.trainroutes.errors;


public class IllegalLocalTimeParsingException extends RuntimeException {


    public IllegalLocalTimeParsingException(String message, int position) {
        super(String.format("Illegal argument %s for Time parsing in character %d!", message,position+1));
    }
}
