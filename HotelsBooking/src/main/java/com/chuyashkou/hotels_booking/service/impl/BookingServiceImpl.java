package com.chuyashkou.hotels_booking.service.impl;

import com.chuyashkou.hotels_booking.exception.RepositoryException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.Booking;
import com.chuyashkou.hotels_booking.repository.impl.BookingRepositoryImpl;
import com.chuyashkou.hotels_booking.service.BookingService;

import java.util.Map;
import java.util.Optional;

public class BookingServiceImpl implements BookingService {

    private static final BookingServiceImpl INSTANCE = new BookingServiceImpl();

    private BookingServiceImpl() {
    }

    public static BookingServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Long create(Booking booking) throws ServiceException {
        try {
            return BookingRepositoryImpl.getInstance().create(booking);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public Optional<Booking> update(Booking booking) throws ServiceException {
        try {
            return BookingRepositoryImpl.getInstance().update(booking);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return BookingRepositoryImpl.getInstance().delete(id);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public Map<Long, Booking> findBookingsByUserId(Long id) throws ServiceException {
        try {
            return BookingRepositoryImpl.getInstance().findBookingsByUserId(id);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public Map<Long, Booking> findBookingsByBookingData(Booking booking) throws ServiceException {
        Map<Long, Booking> bookingMap;
        try {
            bookingMap = BookingRepositoryImpl.getInstance().findAll();
            bookingMap.values().removeIf(b -> !b.getApartment().getId().equals(booking.getApartment().getId()) ||
                    (b.getDateIn().isAfter(booking.getDateOut()) || b.getDateOut().isBefore(booking.getDateIn())));
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return bookingMap;
    }

    @Override
    public Map<Long, Booking> findBookingsByHotelId(Long id) throws ServiceException {
        try {
            return BookingRepositoryImpl.getInstance().findBookingsByHotelId(id);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public Long findBookingIdByApartmentId(Long apartmentId) throws ServiceException {
        try {
            return BookingRepositoryImpl.getInstance().findBookingIdByApartmentId(apartmentId);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
    }
}
