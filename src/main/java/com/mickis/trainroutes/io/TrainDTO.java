package com.mickis.trainroutes.io;

import lombok.*;
//padaryti validaciją laukų
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
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

    private int priceRate;


}
