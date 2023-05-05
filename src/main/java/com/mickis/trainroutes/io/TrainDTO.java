package com.mickis.trainroutes.io;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
//padaryti validaciją laukų
@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class TrainDTO {

    @NonNull
    private String trainNumber;

    @NonNull
    private String cityFrom;

    @NonNull
    private String cityTo;

    @NonNull
    private String departTime;

    @NonNull
    private String arrivalTime;

    @JsonIgnore
    private int priceRate;


}
