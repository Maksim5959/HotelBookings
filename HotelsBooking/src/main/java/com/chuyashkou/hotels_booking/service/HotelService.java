package com.chuyashkou.hotels_booking.service;

import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.model.ApartmentSearchData;
import com.chuyashkou.hotels_booking.model.Hotel;
import com.chuyashkou.hotels_booking.model.HotelsSearchData;

import java.util.Map;
import java.util.Optional;

public interface HotelService extends BaseService<Hotel> {

    /**
     * Find all {@code Hotel} in database
     *
     * @return the {@code Map} of hotels with {@code Long} keys
     * and {@code Hotel} values
     * @throws ServiceException if Repository layer can't do own method
     */
    Map<Long, Hotel> findAll() throws ServiceException;

    /**
     * Find any {@code Hotel} by it id and return {@code Optional}
     * value of it or empty {@code optional}
     *
     * @param id is a {@code Hotel} id by which it will be found
     * @return {@code Optional} of {@code Hotel} if present or
     * empty {@code Optional}
     * @throws ServiceException if Repository layer can't do own method
     */
    Optional<Hotel> findById(Long id) throws ServiceException;

    Optional<Hotel> findHotelByUserId(Long userId) throws ServiceException;

    /**
     * Find {@code Hotel} in database by it data
     *
     * @param hotel include hotel data which needed to find {@code Hotel}
     * @return {@code Optional} of {@code Hotel} value if present or
     * empty {@code Optional}
     * @throws ServiceException if Repository layer can't do own method
     */
    Optional<Hotel> findHotelByHotelData(Hotel hotel) throws ServiceException;

    /**
     * Find some {@code Hotel} in database by {@code HotelsSearchData}
     *
     * @param hotelsSearchData is search hotel data which need for find {@code Hotel}
     * @return the {@code Map} of hotels with {@code Long} keys
     * and {@code Hotel} values
     * @throws ServiceException if Repository layer can't do own method
     */
    Map<Long, Hotel> findHotelsByHotelSearchData(HotelsSearchData hotelsSearchData) throws ServiceException;

    /**
     * Find any {@code Hotel} in database by {@code Hotel} id and filter {@code ApartmentSearchData}
     *
     * @param id                  is a {@code Hotel} id by which it will be found
     * @param apartmentSearchData is apartment data for filter {@code hotel} apartments
     * @return {@code Optional} of {@code Hotel} value if present or
     * empty {@code Optional}
     * @throws ServiceException if Repository layer can't do own method
     */
    Optional<Hotel> findHotelByIdAndApartmentSearchData(long id, ApartmentSearchData apartmentSearchData) throws ServiceException;
}
