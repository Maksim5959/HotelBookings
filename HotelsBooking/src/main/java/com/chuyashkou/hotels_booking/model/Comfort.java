package com.chuyashkou.hotels_booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Comfort {

    private Long id;
    private boolean tv;
    private boolean conditioner;
    private boolean bar;
    private boolean refrigerator;
    private boolean balcony;
    private boolean jacuzzi;
    private boolean breakfast;
    private boolean wifi;
    private boolean transfer;
    private boolean parking;
    private boolean swimmingPool;
    private boolean gym;
    private boolean restaurant;
    private boolean pets;
    private boolean accessibilityFeatures;
}
