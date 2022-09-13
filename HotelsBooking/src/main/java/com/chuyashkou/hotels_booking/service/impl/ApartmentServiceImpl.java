package com.chuyashkou.hotels_booking.service.impl;

import com.chuyashkou.hotels_booking.exception.RepositoryException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.Apartment;
import com.chuyashkou.hotels_booking.repository.impl.ApartmentRepositoryImpl;
import com.chuyashkou.hotels_booking.service.ApartmentService;

import java.util.Optional;

public class ApartmentServiceImpl implements ApartmentService {

    private static final ApartmentServiceImpl INSTANCE = new ApartmentServiceImpl();

    private ApartmentServiceImpl() {
    }

    public static ApartmentServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Long create(Apartment apartment) throws ServiceException {
        try {
            return ApartmentRepositoryImpl.getInstance().create(apartment);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public Optional<Apartment> update(Apartment apartment) throws ServiceException {
        try {
            return ApartmentRepositoryImpl.getInstance().update(apartment);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return ApartmentRepositoryImpl.getInstance().delete(id);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
    }
}
