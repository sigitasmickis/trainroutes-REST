package com.mickis.trainroutes.errors;


public class IllegalCityException extends IllegalArgumentException{
    /**
     * Constructs an {@code IllegalArgumentException} with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public IllegalCityException(String s) {
        super(String.format("The city %s is not found!", s));
    }
}
