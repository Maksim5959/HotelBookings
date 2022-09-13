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
public class User {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Role role;
    private Address address;
}
