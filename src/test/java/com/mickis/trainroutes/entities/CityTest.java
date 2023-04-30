package com.mickis.trainroutes.entities;

import com.mickis.trainroutes.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void whenCityCreated_itIsPersisted() {
        var city = new City("Vilnius");
        var savedCity = cityRepository.save(city);
        var persistedUser = entityManager.find(City.class,savedCity.getId());
        assertEquals(savedCity, persistedUser);
    }

    @Test
    public void whenReadingCity_theyAreFoundByName() {
        var city = new City("Vilnius");
        var persistedCity = entityManager.persist(city);
        var foundCity = cityRepository.findByName("Vilnius");
        assertEquals(persistedCity,foundCity);
        System.out.println(foundCity);
    }

}