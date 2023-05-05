package com.mickis.trainroutes.repository;

import com.mickis.trainroutes.entities.City;
import com.mickis.trainroutes.entities.Train;
import com.mickis.trainroutes.errors.IllegalCityException;
import com.mickis.trainroutes.errors.IllegalLocalTimeParsingException;
import com.mickis.trainroutes.errors.MissingRouteException;
import com.mickis.trainroutes.errors.TrainNotFoundException;
import com.mickis.trainroutes.io.TrainDTO;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainServices {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");


    //        @Autowired
    private TrainRepository trainRepository;

    //        @Autowired
    private CityRepository cityRepository;


    public TrainServices(CityRepository cityRepository, TrainRepository trainRepository) {
        this.cityRepository = cityRepository;
        this.trainRepository = trainRepository;
    }

    public Train createNewTrain(String trainNumber,
                                String cityFrom,
                                String cityTo,
                                String departTime,
                                String arrivalTime, int priceRate) {

        City dbCityFrom = cityRepository.findByName(cityFrom)
                .orElseThrow(() -> new IllegalCityException(cityFrom));
        City dbCityTo = cityRepository.findByName(cityTo)
                .orElseThrow(() -> new IllegalCityException(cityTo));
        LocalTime departTimeLt = parseLocalTime(departTime);
        LocalTime arrivalTimeLt = parseLocalTime(arrivalTime);
        Train train = new Train(trainNumber, dbCityFrom, dbCityTo, departTimeLt, arrivalTimeLt);
        train.setPriceRate(priceRate == 0 ? 1000 : priceRate);
//        var savedTrain = trainRepository.save(train);
        return train;
    }

    public boolean createNewTrainDTO(TrainDTO trainDTO) {
        if (ifTrainIsNotPresent(trainDTO)) {
            City dbCityFrom = cityRepository.findByName(trainDTO.getCityFrom())
                    .orElseThrow(() -> new IllegalCityException(trainDTO.getCityFrom()));
            City dbCityTo = cityRepository.findByName(trainDTO.getCityTo())
                    .orElseThrow(() -> new IllegalCityException(trainDTO.getCityTo()));
            LocalTime departTimeLt = parseLocalTime(trainDTO.getDepartTime());
            LocalTime arrivalTimeLt = parseLocalTime(trainDTO.getArrivalTime());
            Train train = new Train(trainDTO.getTrainNumber(), dbCityFrom, dbCityTo, departTimeLt, arrivalTimeLt);
            train.setPriceRate(train.getPriceRate() == 0 ? 1000 : train.getPriceRate());
            trainRepository.save(train);
            return true;
        } else {
            return false;
        }
    }

    public boolean ifTrainIsNotPresent(TrainDTO trainDTO) {
        Train existingTrain = trainRepository
                .findByTrainNumber(trainDTO.getTrainNumber())
                .orElse(null);
        return (existingTrain == null) ? true : false;

    }

    public Train getTrainByTrainNo(String trainNo) {
        return trainRepository.findByTrainNumber(trainNo)
                .orElseThrow(() -> new TrainNotFoundException(trainNo));
    }


    public LocalTime parseLocalTime(String timeStr) {
        LocalTime localTime;
        try {
            localTime = LocalTime.parse(timeStr, format);
        } catch (DateTimeParseException e) {
            throw new IllegalLocalTimeParsingException(timeStr, e.getErrorIndex());
        }
        return localTime;
    }

    public TrainDTO getTrainDTOFromDb(Train train) {
        var trainDTO = new TrainDTO();
        trainDTO.setTrainNumber(train.getTrainNumber());
        trainDTO.setCityFrom(train.getCityFrom().getName());
        trainDTO.setCityTo(train.getCityTo().getName());
        trainDTO.setDepartTime(train.getDepartTime().format(format));
        trainDTO.setArrivalTime(train.getArrivalTime().format(format));
        return trainDTO;
    }

    public List<TrainDTO> getAllTrains() {
        return trainRepository.findAll()
                .stream()
                .map(t -> getTrainDTOFromDb(t))
                .collect(Collectors.toList());
    }

    public List<TrainDTO> getRouteFromCityToCity(String from, String to) {
        City dbCityFrom = cityRepository.findByName(from)
                .orElseThrow(() -> new IllegalCityException(from));
        City dbCityTo = cityRepository.findByName(to)
                .orElseThrow(() -> new IllegalCityException(to));;
        List<TrainDTO> found = trainRepository.findByCityFromAndCityTo(dbCityFrom, dbCityTo)
                .stream()
                .map(t -> getTrainDTOFromDb(t))
                .collect(Collectors.toList());
        if (!found.isEmpty()) {
            return found;
        } else {
            throw new MissingRouteException(from, to);
        }
    }


    public TrainDTO updateTrain(TrainDTO trainDTO) {
        Train train = trainRepository
                .findByTrainNumber(trainDTO.getTrainNumber()).get();
        train.setTrainNumber(trainDTO.getTrainNumber());
        train.setCityFrom(cityRepository.findByName(trainDTO.getCityFrom())
                .orElseThrow(() -> new IllegalCityException(trainDTO.getCityFrom())));
        train.setCityTo(cityRepository.findByName(trainDTO.getCityTo())
                .orElseThrow(() -> new IllegalCityException(trainDTO.getCityTo())));
        train.setDepartTime(parseLocalTime(trainDTO.getDepartTime()));
        train.setArrivalTime(parseLocalTime(trainDTO.getArrivalTime()));
        trainRepository.save(train);
        return trainDTO;
    }

    public void deleteTrain(String trainNo) {
        Train train = trainRepository.findByTrainNumber(trainNo).orElseThrow();
        trainRepository.deleteById(train.getId());
    }
}
