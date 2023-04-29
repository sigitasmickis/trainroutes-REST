package com.mickis.trainroutes.repository;

import com.mickis.trainroutes.entities.City;
import com.mickis.trainroutes.entities.Train;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Long> {

    List<Train> findByCityFromAndCityTo(City cityFrom, City CityTo, Pageable pageable);

    Train findByCityFrom(City cityFrom);

    Train findByDepartTime(LocalTime departTime);

}
