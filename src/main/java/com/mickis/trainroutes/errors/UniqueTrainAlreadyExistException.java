package com.mickis.trainroutes.errors;

public class UniqueTrainAlreadyExistException extends RuntimeException{

    public UniqueTrainAlreadyExistException(String trainNo) {
        super(String.format("The train %s already exist under UNIQUE constrain ", trainNo));
    }
}
