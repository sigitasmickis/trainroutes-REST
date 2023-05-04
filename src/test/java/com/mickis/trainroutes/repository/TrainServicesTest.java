package com.mickis.trainroutes.repository;

import com.mickis.trainroutes.entities.City;
import com.mickis.trainroutes.entities.Train;
import com.mickis.trainroutes.errors.IllegalCityException;
import com.mickis.trainroutes.errors.IllegalLocalTimeParsingException;
import com.mickis.trainroutes.io.TrainDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TrainServicesTest {

//    @Autowired
//    private TestEntityManager entityManager;

    @MockBean
    CityRepository cityRepository;

    @MockBean
    TrainRepository trainRepository;

    TrainServices trainServices;

    @BeforeEach
    public void startup() {
        this.trainServices = new TrainServices(cityRepository, trainRepository);
    }

    @Test
    public void whenLocalTimeStringIsSupplied_localTimeFormatIsParsedSuccesfully() {
        LocalTime output = LocalTime.of(11, 50);
        assertEquals(output, trainServices.parseLocalTime("11:50"));
    }

    @Test
    public void whenLocalTimeStringIsSupplied_localTimeFormatFailed() {
        assertThrows(IllegalLocalTimeParsingException.class, () -> trainServices.parseLocalTime("12"));
    }

    @Test
    public void whenTrainDataAreSupplied_trainObjectIsCreated() {
        City vilnius = new City("Vilnius");
        City kaunas = new City("Kaunas");

        Mockito.when(cityRepository.findByName(vilnius.getName())).thenReturn(Optional.of(vilnius));
        Mockito.when(cityRepository.findByName(kaunas.getName())).thenReturn(Optional.of(kaunas));

        var savedTrain = trainServices.createNewTrain(
                "Tr-065",
                "Vilnius", "Kaunas", "09:15", "10:20", 3500);
        System.out.println(savedTrain);
    }

    @Test
    public void whenTrainDTOisSupplied_trainObjectIsCreated() {
        City vilnius = new City("Vilnius");
        City kaunas = new City("Kaunas");
        Train train = new Train("Tr-065",
                vilnius, kaunas, LocalTime.of(9,15), LocalTime.of(10,20));
        train.setPriceRate(1000);

        Mockito.when(cityRepository.findByName(vilnius.getName())).thenReturn(Optional.of(vilnius));
        Mockito.when(cityRepository.findByName(kaunas.getName())).thenReturn(Optional.of(kaunas));

        TrainDTO trainDTO = new TrainDTO("Tr-065",
                "Vilnius", "Kaunas", "09:15", "10:20");
        Mockito.when(trainRepository.save(train)).thenReturn(train);
        trainServices.createNewTrain(trainDTO);

        Mockito.verify(trainRepository).save(any());

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


        assertThrows(IllegalCityException.class, () -> trainServices.createNewTrain(
                "Tr-065",
                "Vilnius", "Kaunas", "09:15", "10:20", 100));
    }

    @Test
    public void givenTrainInstanceIsSuplied_trainDTOReturnedSuccesfully() {
        var cityVilnius = new City("Vilnius");
        var cityKaunas = new City("Kaunas");
        var departTime = LocalTime.of(19, 15);
        var arriveTime = LocalTime.of(20, 30);
        var train = new Train("Tr-065", cityVilnius, cityKaunas,
                departTime, arriveTime);
        var trainDTOOutput = new TrainDTO("Tr-065",
                "Vilnius", "Kaunas", "19:15", "20:30");
        var savedDTO = trainServices.getTrainDTOFromDb(train);
        assertEquals(trainDTOOutput, savedDTO);

    }


}