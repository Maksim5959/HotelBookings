package com.chuyashkou.hotels_booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Apartment {

    private Long id;
    private String name;
    private Double price;
    private Category category;
    private Integer singleBedCount;
    private Integer doubleBedCount;
    private Comfort comfort;
    private Long hotelId;
    private Map<Long, Booking> bookings;
}
