package com.mickis.trainroutes.io;

import com.mickis.trainroutes.entities.City;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class CitiesDTO {

    @NonNull
    private List<City> sitiesDTO;

    public static CitiesDTO of(List<City> citiesDTO) {
        return new CitiesDTO(citiesDTO);
    }
}
