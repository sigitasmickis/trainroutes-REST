package com.mickis.trainroutes.repository;

import com.mickis.trainroutes.entities.City;
import com.mickis.trainroutes.entities.Train;
import com.mickis.trainroutes.errors.IllegalCityError;
import com.mickis.trainroutes.errors.IllegalLocalTimeFormat;
import com.mickis.trainroutes.io.TrainDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class TrainServices {

////    @Autowired
//    private TrainRepository trainRepository;

//    @Autowired
    private CityRepository cityRepository;

    public TrainServices(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Train createNewTrain(String trainNumber,
                                String cityFrom,
                                String cityTo,
                                String departTime,
                                String arrivalTime, int priceRate) {

        City dbCityFrom = cityRepository.findByName(cityFrom).orElseThrow(IllegalCityError::new);
        City dbCityTo = cityRepository.findByName(cityTo).orElseThrow(IllegalCityError::new);
        LocalTime departTimeLt = parseLocalTime(departTime);
        LocalTime arrivalTimeLt = parseLocalTime(arrivalTime);
        Train train = new Train(trainNumber, dbCityFrom, dbCityTo, departTimeLt, arrivalTimeLt);
        if (priceRate != 0) train.setPriceRate(priceRate);
        return train;
    }

    public Train createNewTrainDTO(TrainDTO trainDTO) {

        City dbCityFrom = cityRepository.findByName(trainDTO.getCityFrom()).orElseThrow(IllegalCityError::new);
        City dbCityTo = cityRepository.findByName(trainDTO.getCityTo()).orElseThrow(IllegalCityError::new);
        LocalTime departTimeLt = parseLocalTime(trainDTO.getDepartTime());
        LocalTime arrivalTimeLt = parseLocalTime(trainDTO.getArrivalTime());
        Train train = new Train(trainDTO.getTrainNumber(), dbCityFrom, dbCityTo, departTimeLt, arrivalTimeLt);
        if (trainDTO.getPriceRate() != 0) train.setPriceRate(trainDTO.getPriceRate());
        return train;
    }

    public LocalTime parseLocalTime(String timeStr) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime;
        try {
            localTime = LocalTime.parse(timeStr, format);
        } catch (DateTimeParseException e) {
            throw new IllegalLocalTimeFormat();
        }
        return localTime;
    }


}
