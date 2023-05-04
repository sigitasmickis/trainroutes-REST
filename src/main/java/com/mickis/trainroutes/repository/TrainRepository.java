package com.mickis.trainroutes.repository;

import com.mickis.trainroutes.entities.City;
import com.mickis.trainroutes.entities.Train;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {

    List<Train> findByCityFromAndCityTo(City cityFrom, City CityTo);

    Train findByCityFrom(City cityFrom);

    Train findByDepartTime(LocalTime departTime);

    Optional<Train> findByTrainNumber(String name);

}
