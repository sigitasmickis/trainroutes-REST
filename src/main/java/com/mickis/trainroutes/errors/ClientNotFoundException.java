package com.mickis.trainroutes.errors;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long userId) {
        super(String.format("Requested client with userId %d not found in db", userId));
    }
}
