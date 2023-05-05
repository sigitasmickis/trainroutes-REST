package com.mickis.trainroutes.io;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode
public class TicketDTO {

    @NonNull
    private long userId;

    @NonNull
    private String trainNumber;

    private String cityFrom;

    private String cityTo;

    private String departTime;

    private String arrivalTime;

    @NonNull
    private int userPrice;



}
