package com.chuyashkou.hotels_booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Address {

    private Long id;
    private String country;
    private String city;
    private String street;
    private String house;
    private String building;
    private String apartmentNumber;
}
