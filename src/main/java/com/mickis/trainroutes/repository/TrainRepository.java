package com.mickis.trainroutes.repository;

import com.mickis.trainroutes.entities.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Long> {

}
