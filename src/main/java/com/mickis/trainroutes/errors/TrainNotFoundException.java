package com.mickis.trainroutes.errors;

public class TrainNotFoundException extends RuntimeException{
    public TrainNotFoundException(String trainNo) {
        super("Could not find train " + trainNo);
    }
}
