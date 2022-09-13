package com.chuyashkou.hotels_booking.service;

import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.model.User;

import java.util.Map;
import java.util.Optional;

public interface UserService extends BaseService<User> {

    /**
     * Find all {@code User} in database
     *
     * @return the {@code Map} of hotels with {@code Long} keys
     * and {@code User} values
     * @throws ServiceException if Repository layer can't do own method
     */
    Map<Long, User> findAll() throws ServiceException;

    /**
     * Update {@code User} password in database by new value of it
     *
     * @param user encoding password value of {@code User}
     * @return {@code boolean} value - {@code true} if success
     * or {@code false} if fail
     * @throws ServiceException if Repository layer can't do own method
     */
    boolean updateUserPassword(User user) throws ServiceException;

    /**
     * Delete any user {@code Address} by it id
     *
     * @param user include {@code Address} id which need to delete user {@code Address}
     * @return {@code Optional} of {@code User} without {@code Address} if value present
     * or empty {@code Optional}
     * @throws ServiceException if Repository layer can't do own method
     */
    Optional<User> deleteUserAddress(User user) throws ServiceException;

    /**
     * Create new user {@code Address} value in database
     *
     * @param user include new {@code Address}
     * @return {@code Optional} of {@code User} with {@code Address} if value present
     * or empty {@code Optional}
     * @throws ServiceException if Repository layer can't do own method
     */
    Optional<User> createUserAddress(User user) throws ServiceException;

    /**
     * Update user {@code Address} in database by new value of it
     *
     * @param user include new address data of user {@code Address}
     * @return {@code Optional} of {@code User} with new {@code Address} if value present
     * or empty {@code Optional}
     * @throws ServiceException if Repository layer can't do own method
     */
    Optional<User> updateUserAddress(User user) throws ServiceException;

    /**
     * Find in database {@code User} by his email and excluding his id value
     *
     * @param email its a {@code String} email value
     * @param id    its a {@code User} id
     * @return {@code Optional} of {@code User} if value present
     * or empty {@code Optional}
     * @throws ServiceException if Repository layer can't do own method
     */
    Optional<User> findByEmailExcludingId(String email, Long id) throws ServiceException;

    /**
     * Find in database {@code User} by email value
     *
     * @param email its {@code String} value of the email
     * @return {@code Optional} of {@code User} if value present
     * or empty {@code Optional}
     * @throws ServiceException if Repository layer can't do own method
     */
    Optional<User> findByEmail(String email) throws ServiceException;

    /**
     * Check in database {@code User} by his email and password value
     *
     * @param email    its a {@code String} email value
     * @param password its a {@code String} encoding password value
     * @return {@code Optional} of {@code User} if value present
     * or empty {@code Optional}
     * @throws ServiceException if Repository layer can't do own method
     */
    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;
}
