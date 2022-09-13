package com.chuyashkou.hotels_booking.command.impl;

import com.chuyashkou.hotels_booking.command.Command;
import com.chuyashkou.hotels_booking.command.CommandResult;
import com.chuyashkou.hotels_booking.command.NavigationType;
import com.chuyashkou.hotels_booking.exception.CommandException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.Address;
import com.chuyashkou.hotels_booking.model.Hotel;
import com.chuyashkou.hotels_booking.model.Stars;
import com.chuyashkou.hotels_booking.service.HotelService;
import com.chuyashkou.hotels_booking.service.impl.HotelServiceImpl;
import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class UpdateHotelDataCommandImpl implements Command {
    private static final String LEGAL_NAME = "legalName";
    private static final String BRAND = "brand";
    private static final String STARS = "stars";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String HOUSE = "house";
    private static final String BUILDING = "building";
    private static final String HOTEL_ID = "hotelId";
    private static final String HOTEL = "hotel";
    private static final String UPDATE_HOTEL_ERROR_NAME = "updateHotelError";
    private static final boolean ERROR_FLAG = true;


    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult(PageManager.getPageURI(PageMappingConstant.HOTEL_MANAGER_UPDATE_PAGE.getKey()),
                NavigationType.FORWARD);
        HotelService hotelService = HotelServiceImpl.getInstance();
        Hotel hotel = this.buildHotel(req);
        try {
            Optional<Hotel> updateHotel = Optional.empty();
            AtomicReference<Optional<Hotel>> hotelByHotelData = new AtomicReference<>(hotelService.findHotelByHotelData(hotel));
            hotelByHotelData.get().ifPresent(h -> {
                if (h.getId().equals(hotel.getId())) {
                    hotelByHotelData.set(Optional.empty());
                }
            });
            if (hotelByHotelData.get().isEmpty()) {
                updateHotel = hotelService.update(hotel);
            }
            if (updateHotel.isPresent()) {
                req.setAttribute(HOTEL, updateHotel.get());
            } else {
                Optional<Hotel> oldHotel = hotelService.findById(Long.parseLong(req.getParameter(HOTEL_ID)));
                req.setAttribute(HOTEL, oldHotel.orElse(new Hotel()));
                req.setAttribute(UPDATE_HOTEL_ERROR_NAME, ERROR_FLAG);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }

    private Hotel buildHotel(HttpServletRequest req) {
        return Hotel.builder()
                .id(Long.parseLong(req.getParameter(HOTEL_ID)))
                .legalName(req.getParameter(LEGAL_NAME))
                .brand(req.getParameter(BRAND))
                .stars(Stars.values()[Integer.parseInt(req.getParameter(STARS))])
                .isRegistered(true)
                .address(Address.builder()
                        .country(req.getParameter(COUNTRY))
                        .city(req.getParameter(CITY))
                        .street(req.getParameter(STREET))
                        .house(req.getParameter(HOUSE))
                        .building(req.getParameter(BUILDING))
                        .build())
                .build();
    }
}
