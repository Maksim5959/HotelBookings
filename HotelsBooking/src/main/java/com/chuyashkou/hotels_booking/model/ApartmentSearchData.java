package com.chuyashkou.hotels_booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApartmentSearchData {

    private long minHotelPrice;
    private long maxHotelPrice;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private String category;
}
