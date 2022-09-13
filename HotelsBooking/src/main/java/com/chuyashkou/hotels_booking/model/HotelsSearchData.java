package com.chuyashkou.hotels_booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HotelsSearchData {

    private String city;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private int adultsCount;
    private int childrenCount;
    private int roomsCount;
    private HotelsFilter hotelsFilter;
}
