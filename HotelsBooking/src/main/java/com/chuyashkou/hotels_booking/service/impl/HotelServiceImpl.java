package com.chuyashkou.hotels_booking.service.impl;

import com.chuyashkou.hotels_booking.exception.RepositoryException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.*;
import com.chuyashkou.hotels_booking.repository.impl.HotelRepositoryImpl;
import com.chuyashkou.hotels_booking.service.HotelService;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class HotelServiceImpl implements HotelService {

    private static final HotelServiceImpl INSTANCE = new HotelServiceImpl();

    private HotelServiceImpl() {
    }

    public static HotelServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Hotel> findHotelByUserId(Long userId) throws ServiceException {
        Optional<Hotel> optionalHotel;
        try {
            optionalHotel = HotelRepositoryImpl.getInstance().findHotelByUserId(userId);
            optionalHotel.ifPresent(hotel -> {
                if (hotel.getApartments().containsKey(0L) && hotel.getApartments().get(0L).getName() == null)
                    hotel.setApartments(new LinkedHashMap<>());
            });
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return optionalHotel;
    }

    @Override
    public Optional<Hotel> findHotelByHotelData(Hotel hotel) throws ServiceException {
        Optional<Hotel> optionalHotel;
        try {
            optionalHotel = HotelRepositoryImpl.getInstance().findHotelByHotelData(hotel);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return optionalHotel;
    }

    @Override
    public Map<Long, Hotel> findHotelsByHotelSearchData(HotelsSearchData hotelsSearchData) throws ServiceException {
        Map<Long, Hotel> hotels;
        Map<Long, Hotel> sortedHotelsMap;
        try {
            hotels = HotelRepositoryImpl.getInstance().findHotelByHotelSearchData(hotelsSearchData);
            this.removeBookedApartments(hotels, hotelsSearchData);
            this.removeInvalidHotels(hotels, hotelsSearchData);
            sortedHotelsMap = this.filterHotels(hotels, hotelsSearchData);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return sortedHotelsMap;
    }

    @Override
    public Map<Long, Hotel> findAll() throws ServiceException {
        Map<Long, Hotel> hotels;
        try {
            hotels = HotelRepositoryImpl.getInstance().findAll();
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return hotels;
    }

    @Override
    public Optional<Hotel> findById(Long id) throws ServiceException {
        Optional<Hotel> hotel;
        try {
            hotel = HotelRepositoryImpl.getInstance().findById(id);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return hotel;
    }

    @Override
    public Optional<Hotel> findHotelByIdAndApartmentSearchData(long id, ApartmentSearchData apartmentSearchData) throws ServiceException {
        Optional<Hotel> optionalHotel;
        Hotel hotel = null;
        try {
            optionalHotel = HotelRepositoryImpl.getInstance().findById(id);
            if (optionalHotel.isPresent()) {
                hotel = this.removeApartmentsWithInvalidPrice(optionalHotel.get(), apartmentSearchData);
                this.removeBookedApartments(hotel, apartmentSearchData);
                this.removeApartmentsWithInvalidPrice(hotel, apartmentSearchData);
                this.removeApartmentsWithInvalidCategory(hotel, apartmentSearchData);
            }
            optionalHotel = Optional.ofNullable(hotel);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return optionalHotel;
    }

    @Override
    public Long create(Hotel hotel) throws ServiceException {
        Long id;
        try {
            id = HotelRepositoryImpl.getInstance().create(hotel);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return id;
    }

    @Override
    public Optional<Hotel> update(Hotel hotel) throws ServiceException {
        try {
            return HotelRepositoryImpl.getInstance().update(hotel);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return HotelRepositoryImpl.getInstance().delete(id);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    private Hotel removeApartmentsWithInvalidPrice(Hotel hotel, ApartmentSearchData apartmentSearchData) {
        hotel.getApartments().values().removeIf(apartment -> apartment.getPrice() < apartmentSearchData.getMinHotelPrice()
                || apartment.getPrice() > apartmentSearchData.getMaxHotelPrice());
        return hotel;
    }

    private void removeApartmentsWithInvalidCategory(Hotel hotel, ApartmentSearchData apartmentSearchData) {
        if (!apartmentSearchData.getCategory().equals("all")) {
            hotel.getApartments().values().removeIf(apartment -> apartment.getCategory()
                    != Category.valueOf(apartmentSearchData.getCategory().toUpperCase()));
        }
    }

    private void removeBookedApartments(Map<Long, Hotel> hotels, HotelsSearchData hotelsSearchData) {
        for (Hotel hotel : hotels.values()) {
            hotel.getApartments().keySet().removeIf(k -> {
                Map<Long, Apartment> apartments = hotels.get(hotel.getId()).getApartments();
                Map<Long, Booking> bookings = apartments.get(k).getBookings();
                for (Booking booking : bookings.values()) {
                    if (booking.isConfirm() && !(booking.getDateIn().isAfter(hotelsSearchData.getDateOut())
                            || booking.getDateOut().isBefore(hotelsSearchData.getDateIn()))) {
                        return true;
                    }
                }
                return false;
            });
        }
    }

    private void removeBookedApartments(Hotel hotel, ApartmentSearchData apartmentSearchData) {
        hotel.getApartments().keySet().removeIf(k -> {
            Map<Long, Booking> bookings = hotel.getApartments().get(k).getBookings();
            for (Booking booking : bookings.values()) {
                if (booking.isConfirm() && !(booking.getDateIn().isAfter(apartmentSearchData.getDateOut())
                        || booking.getDateOut().isBefore(apartmentSearchData.getDateIn()))) {
                    return true;
                }
            }
            return false;
        });
    }

    private void removeInvalidHotels(Map<Long, Hotel> hotels, HotelsSearchData hotelsSearchData) {
        hotels.keySet().removeIf(k -> hotels.get(k).getApartments().size() < hotelsSearchData.getRoomsCount());
        hotels.keySet().removeIf(k -> {
            Map<Long, Apartment> apartments = hotels.get(k).getApartments();
            int adultsSleepingPlaces = 0;
            int childrenSleepingPlaces = 0;
            for (Apartment apartment : apartments.values()) {
                adultsSleepingPlaces += apartment.getDoubleBedCount() * 2 + apartment.getSingleBedCount();
                childrenSleepingPlaces += apartment.getDoubleBedCount() + apartment.getSingleBedCount();
            }
            return adultsSleepingPlaces < hotelsSearchData.getAdultsCount() || childrenSleepingPlaces < hotelsSearchData.getChildrenCount();
        });
    }

    private Map<Long, Hotel> filterHotels(Map<Long, Hotel> hotels, HotelsSearchData hotelsSearchData) {
        return hotels.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(hotelsSearchData.getHotelsFilter().getComparator()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (h1, h2) -> h2, LinkedHashMap::new));
    }
}
