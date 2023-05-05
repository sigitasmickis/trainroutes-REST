package com.mickis.trainroutes.errors;

import com.mickis.trainroutes.io.TrainDTO;

public class UpdatingTrainDTOBodyAndTrainNumberMismatchException extends RuntimeException {
    public UpdatingTrainDTOBodyAndTrainNumberMismatchException(String trainNo, TrainDTO trainDTO) {
        super(String.format("Update request number %s and data content number %s mismatch!",
                trainNo,trainDTO.getTrainNumber()));
    }
}
