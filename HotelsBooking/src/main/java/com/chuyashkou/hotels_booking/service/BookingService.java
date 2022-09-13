package com.chuyashkou.hotels_booking.service;

import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.model.Booking;

import java.util.Map;

public interface BookingService extends BaseService<Booking> {

    /**
     * Find some {@code Booking} by user id
     *
     * @param id is a {@code User} id by which bookings will be found
     * @return the {@code Map} of bookings with {@code Long} keys
     * and {@code Booking} values
     * @throws ServiceException if Repository layer can't do own method
     */
    Map<Long, Booking> findBookingsByUserId(Long id) throws ServiceException;

    /**
     * Find some {@code Booking} in database by booking data
     *
     * @param booking include data by which bookings will be found
     * @return the {@code Map} of bookings with {@code Long} keys
     * and {@code Booking} values
     * @throws ServiceException if Repository layer can't do own method
     */
    Map<Long, Booking> findBookingsByBookingData(Booking booking) throws ServiceException;

    /**
     * Find some {@code Booking} by hotel id
     *
     * @param id is a {@code Hotel} id by which bookings will be found
     * @return the {@code Map} of bookings with {@code Long} keys
     * and {@code Booking} values
     * @throws ServiceException if Repository layer can't do own method
     */
    Map<Long, Booking> findBookingsByHotelId(Long id) throws ServiceException;

    /**
     * Find id of {@code Booking} by id of {@code Apartment}
     *
     * @param apartmentId is a {@code Apartment} id by which booking id will be found
     * @return id number of {@code Booking}
     * @throws ServiceException if Repository layer can't do own method
     */
    Long findBookingIdByApartmentId(Long apartmentId) throws ServiceException;
}
