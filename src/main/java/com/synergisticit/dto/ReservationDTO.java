package com.synergisticit.dto;

import com.synergisticit.domain.BookStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDTO {
    private Long reservationNumber;
    private int checkedBags;
    private BookStatus status;
}
