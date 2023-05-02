package com.mickis.trainroutes.repository;

import com.mickis.trainroutes.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByName(String name);
}
