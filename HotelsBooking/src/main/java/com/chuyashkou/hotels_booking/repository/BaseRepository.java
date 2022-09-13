package com.chuyashkou.hotels_booking.repository;

import com.chuyashkou.hotels_booking.exception.RepositoryException;

import java.util.Optional;

public interface BaseRepository<T> {

    /**
     * Create new {@code T} value in database
     *
     * @param t is a value of {@code T}
     * @return {@code Long} id value of {@code T}
     * @throws RepositoryException if have {@code ConnectionPool} error or
     *                             any database error
     */
    Long create(T t) throws RepositoryException;

    /**
     * Update {@code T} value in database by new value of it
     *
     * @param t is a new value of {@code T}
     * @return {@code Optional} of {@code T} if present or
     * empty {@code Optional}
     * @throws RepositoryException if have {@code ConnectionPool} error or
     *                             any database error
     */
    Optional<T> update(T t) throws RepositoryException;

    /**
     * Delete any {@code T} by it id and return {@code true}
     * if {@code T} was successfully deleted or {@code false}
     * if it failed
     *
     * @param id which need to delete
     * @return {@code boolean} value - {@code true} if success
     * or {@code false} if fail
     * @throws RepositoryException if have {@code ConnectionPool} error or
     *                             any database error
     */
    boolean delete(Long id) throws RepositoryException;
}
