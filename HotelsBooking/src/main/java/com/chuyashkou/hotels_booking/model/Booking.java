package com.chuyashkou.hotels_booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Booking {

    private Long id;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private LocalDateTime addTime;
    private LocalDateTime confirmTime;
    private Double totalPrice;
    private boolean isConfirm;
    private Hotel hotel;
    private User user;
    private Apartment apartment;
}
