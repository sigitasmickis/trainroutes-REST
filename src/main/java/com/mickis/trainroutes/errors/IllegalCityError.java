package com.mickis.trainroutes.errors;

public class IllegalCityError extends IllegalArgumentException{
    /**
     * Constructs an {@code IllegalArgumentException} with no
     * detail message.
     */
    public IllegalCityError() {
        super("IllegalCityError");
    }
}
