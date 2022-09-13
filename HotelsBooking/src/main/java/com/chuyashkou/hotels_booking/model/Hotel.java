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
public class Hotel {

    private Long id;
    private String legalName;
    private String brand;
    private Address address;
    private Stars stars;
    private boolean isRegistered;
    private Long managerId;
    private Map<Long, Apartment> apartments;
}
