package com.mickis.trainroutes.repository;

import com.mickis.trainroutes.entities.City;
import com.mickis.trainroutes.errors.IllegalCityError;
import com.mickis.trainroutes.errors.IllegalLocalTimeFormat;
import com.mickis.trainroutes.io.TrainDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TrainServicesTest {

    @MockBean
    CityRepository cityRepository;

    TrainServices trainServices;

    @BeforeEach
    public void startup() {
        this.trainServices = new TrainServices(cityRepository);
    }

    @Test
    public void whenLocalTimeStringIsSupplied_localTimeFormatIsParsedSuccesfully() {
        LocalTime output = LocalTime.of(11, 50);
        assertEquals(output, trainServices.parseLocalTime("11:50"));
    }

    @Test
    public void whenLocalTimeStringIsSupplied_localTimeFormatFailed() {
        assertThrows(IllegalLocalTimeFormat.class, () -> trainServices.parseLocalTime("12"));
    }

    @Test
    public void whenTrainDataAreSupplied_trainObjectIsCreated() {
        City vilnius = new City("Vilnius");
        City kaunas = new City("Kaunas");

        Mockito.when(cityRepository.findByName(vilnius.getName())).thenReturn(Optional.of(vilnius));
        Mockito.when(cityRepository.findByName(kaunas.getName())).thenReturn(Optional.of(kaunas));

        System.out.println(trainServices.createNewTrain(
                "Tr-065",
                "Vilnius", "Kaunas", "09:15", "10:20", 100));
    }

    @Test
    public void whenTrainDTOisSupplied_trainObjectIsCreated() {
        City vilnius = new City("Vilnius");
        City kaunas = new City("Kaunas");

        Mockito.when(cityRepository.findByName(vilnius.getName())).thenReturn(Optional.of(vilnius));
        Mockito.when(cityRepository.findByName(kaunas.getName())).thenReturn(Optional.of(kaunas));

        TrainDTO trainDTO = new TrainDTO("Tr-065",
                "Vilnius", "Kaunas", "09:15", "10:20");

        System.out.println(trainServices.createNewTrainDTO(trainDTO));
    }

    @Test
    public void whenTrainDataAreSupplied_trainObjectIsCreatedWithoutPriceRateSet() {
        City vilnius = new City("Vilnius");
        City kaunas = new City("Kaunas");

        Mockito.when(cityRepository.findByName(vilnius.getName())).thenReturn(Optional.of(vilnius));
        Mockito.when(cityRepository.findByName(kaunas.getName())).thenReturn(Optional.of(kaunas));

        System.out.println(trainServices.createNewTrain(
                "Tr-065",
                "Vilnius", "Kaunas", "09:15", "10:20", 0));
    }

    @Test
    public void whenTrainDataAreSupplied_exceptionForIllegalCityNameIsThrown() {
        City vilnius = new City("Vilnius");

        Mockito.when(cityRepository.findByName(vilnius.getName())).thenReturn(Optional.of(vilnius));
        Mockito.when(cityRepository.findByName("Kaunas")).thenReturn(Optional.empty());


        assertThrows(IllegalCityError.class, () -> trainServices.createNewTrain(
                "Tr-065",
                "Vilnius", "Kaunas", "09:15", "10:20", 100));
    }



}