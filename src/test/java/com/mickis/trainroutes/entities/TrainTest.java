package com.mickis.trainroutes.entities;

import com.mickis.trainroutes.repository.CityRepository;
import com.mickis.trainroutes.repository.TrainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TrainTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private CityRepository cityRepository;

    private List<Train> createCities() {
        var cityVilnius = cityRepository.save(new City("Vilnius"));
        var cityKaunas = cityRepository.save(new City("Kaunas"));
        var departTime = LocalTime.of(19, 15);
        var arriveTime = LocalTime.of(20, 30);
        var train = new Train("Tr-065", cityVilnius, cityKaunas,
                departTime, arriveTime);
        var savedTrain = trainRepository.save(train);
        var persistedTrain = entityManager.find(Train.class, savedTrain.getId());
        return List.of(
                savedTrain,
                persistedTrain
        );

    }

    @Test
    public void whenCreatingTrain_itIsPersisted() {
        List<Train> oneTrain = createCities();
        assertEquals(oneTrain.get(1), oneTrain.get(0));
    }

    @Test
    public void givenOneTrain_whenFindRoute_itIsFound() {
        List<Train> oneTrain = createCities();
//       List<Train> found = trainRepository.findByCityFromAndCityTo(1L, 2L);
//       assertEquals(oneTrain.get(1), found);

    }

    @Test
    public void givenOneTrain_whenFindDepartTime_itIsFound() {
        var cityVilnius = cityRepository.save(new City("Vilnius"));
        var cityKaunas = cityRepository.save(new City("Kaunas"));
        var departTime = LocalTime.of(19, 15);
        var arriveTime = LocalTime.of(20, 30);
        var train = new Train("Tr-065", cityVilnius, cityKaunas,
                departTime, arriveTime);
        var savedTrain = trainRepository.save(train);
        var persistedTrain = entityManager.find(Train.class, savedTrain.getId());
        var found = trainRepository.findByDepartTime(departTime);
        assertEquals(persistedTrain, found);

    }

    @Test
    public void givenOneTrain_whenFindCityFromId_itIsFound() {
        var cityVilnius = cityRepository.save(new City("Vilnius"));
        var cityKaunas = cityRepository.save(new City("Kaunas"));
        var departTime = LocalTime.of(19, 15);
        var arriveTime = LocalTime.of(20, 30);
        var train = new Train("Tr-065", cityVilnius, cityKaunas,
                departTime, arriveTime);
        train.setPriceRate(100);
        var savedTrain = trainRepository.save(train);
        var persistedTrain = entityManager.find(Train.class, savedTrain.getId());
        var found = trainRepository.findByCityFrom(cityVilnius);
        assertEquals(persistedTrain, found);
        assertEquals(100, found.getPriceRate());
        System.out.println(found);

    }

    @Test
    public void givenOneTrain_whenFindCRoute_itIsNotFound() {
        var cityVilnius = cityRepository.save(new City("Vilnius"));
        var cityKaunas = cityRepository.save(new City("Kaunas"));
        var cityKlaipeda = cityRepository.save(new City("Klaipėda"));
        var departTime = LocalTime.of(19, 15);
        var arriveTime = LocalTime.of(20, 30);
        var train = new Train("Tr-065", cityVilnius, cityKaunas,
                departTime, arriveTime);

        var savedTrain = trainRepository.save(train);
        List<Train> found = trainRepository
                .findByCityFromAndCityTo(cityVilnius, cityKlaipeda);
        assertEquals(List.of(), found);
        System.out.println(found);
    }


}