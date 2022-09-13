package com.chuyashkou.hotels_booking.service;

import com.chuyashkou.hotels_booking.exception.ServiceException;

import java.util.Optional;

public interface BaseService<T> {

    /**
     * Return new {@code T} value
     *
     * @param t is a value of {@code T}
     * @return {@code Long} id value of {@code T}
     * @throws ServiceException if Repository layer can't do own method
     */
    Long create(T t) throws ServiceException;

    /**
     * Update {@code T} value
     *
     * @param t is a value of {@code T}
     * @return {@code Optional} of {@code T} if present or
     * empty {@code Optional}
     * @throws ServiceException if Repository layer can't do own method
     */
    Optional<T> update(T t) throws ServiceException;

    /**
     * Delete any {@code T} value
     *
     * @param id which need to delete
     * @return {@code boolean} value - {@code true} if success
     * or {@code false} if fail
     * @throws ServiceException if Repository layer can't do own method
     */
    boolean delete(Long id) throws ServiceException;
}
