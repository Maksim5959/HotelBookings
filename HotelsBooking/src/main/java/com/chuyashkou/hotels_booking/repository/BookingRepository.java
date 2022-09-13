package com.chuyashkou.hotels_booking.repository;

import com.chuyashkou.hotels_booking.exception.RepositoryException;
import com.chuyashkou.hotels_booking.model.Booking;

import java.util.Map;

public interface BookingRepository extends BaseRepository<Booking> {

    /**
     * Find all {@code Booking} in database
     *
     * @return the {@code Map} of bookings with {@code Long} keys
     * and {@code Booking} values
     * @throws RepositoryException if have {@code ConnectionPool} error or
     *                             any database error
     */
    Map<Long, Booking> findAll() throws RepositoryException;

    /**
     * Find some {@code Booking} by hotel id
     *
     * @param id is a {@code Hotel} id by which bookings will be found
     * @return the {@code Map} of bookings with {@code Long} keys
     * and {@code Booking} values
     * @throws RepositoryException if have {@code ConnectionPool} error or
     *                             any database error
     */
    Map<Long, Booking> findBookingsByHotelId(Long id) throws RepositoryException;

    /**
     * Find some {@code Booking} by user id
     *
     * @param id is a {@code User} id by which bookings will be found
     * @return the {@code Map} of bookings with {@code Long} keys
     * and {@code Booking} values
     * @throws RepositoryException if have {@code ConnectionPool} error or
     *                             any database error
     */
    Map<Long, Booking> findBookingsByUserId(Long id) throws RepositoryException;

    /**
     * Find id of {@code Booking} by id of {@code Apartment}
     *
     * @param apartmentId is a {@code Apartment} id by which booking id will be found
     * @return id number of {@code Booking}
     * @throws RepositoryException if have {@code ConnectionPool} error or
     *                             any database error
     */
    Long findBookingIdByApartmentId(Long apartmentId) throws RepositoryException;
}
