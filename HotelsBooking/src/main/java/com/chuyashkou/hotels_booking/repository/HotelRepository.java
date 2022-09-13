package com.chuyashkou.hotels_booking.repository;

import com.chuyashkou.hotels_booking.exception.RepositoryException;
import com.chuyashkou.hotels_booking.model.Hotel;
import com.chuyashkou.hotels_booking.model.HotelsSearchData;

import java.util.Map;
import java.util.Optional;

public interface HotelRepository extends BaseRepository<Hotel> {

    /**
     * Find all {@code Hotel} in database
     *
     * @return the {@code Map} of hotels with {@code Long} keys
     * and {@code Hotel} values
     * @throws RepositoryException if have {@code ConnectionPool} error or
     *                             any database error
     */
    Map<Long, Hotel> findAll() throws RepositoryException;

    /**
     * Find any {@code Hotel} by it id and return {@code Optional}
     * value of it or empty {@code optional}
     *
     * @param id is a {@code Hotel} id by which it will be found
     * @return {@code Optional} of {@code Hotel} if present or
     * empty {@code Optional}
     * @throws RepositoryException if have {@code ConnectionPool} error or
     *                             any database error
     */
    Optional<Hotel> findById(Long id) throws RepositoryException;

    /**
     * Find {@code Hotel} in database by it data
     *
     * @param hotel include hotel data which needed to find {@code Hotel}
     * @return {@code Optional} of {@code Hotel} value if present or
     * empty {@code Optional}
     * @throws RepositoryException if have {@code ConnectionPool} error or
     *                             any database error
     */
    Optional<Hotel> findHotelByHotelData(Hotel hotel) throws RepositoryException;

    /**
     * Find some {@code Hotel} in database by {@code HotelsSearchData}
     *
     * @param hotelsSearchData is search hotel data which need for find {@code Hotel}
     * @return the {@code Map} of hotels with {@code Long} keys
     * and {@code Hotel} values
     * @throws RepositoryException if have {@code ConnectionPool} error or
     *                             any database error
     */
    Map<Long, Hotel> findHotelByHotelSearchData(HotelsSearchData hotelsSearchData) throws RepositoryException;

    /**
     * Find any {@code Hotel} by {@code User} id and return {@code Optional}
     * value of it or empty {@code optional}
     *
     * @param userId is a {@code User} id by which {@code Hotel} will be found
     * @return {@code Optional} of {@code Hotel} if present or
     * empty {@code Optional}
     * @throws RepositoryException if have {@code ConnectionPool} error or
     *                             any database error
     */
    Optional<Hotel> findHotelByUserId(Long userId) throws RepositoryException;
}
