package com.mickis.trainroutes.io;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class TicketDTO {

    @NonNull
    private String trainNumber;

    @NonNull
    private long userId;

    @NonNull
    private int quantity;

}
