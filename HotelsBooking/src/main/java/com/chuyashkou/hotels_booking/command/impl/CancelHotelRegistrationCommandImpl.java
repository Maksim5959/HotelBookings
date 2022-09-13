package com.chuyashkou.hotels_booking.command.impl;

import com.chuyashkou.hotels_booking.command.Command;
import com.chuyashkou.hotels_booking.command.CommandResult;
import com.chuyashkou.hotels_booking.command.NavigationType;
import com.chuyashkou.hotels_booking.exception.CommandException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.Hotel;
import com.chuyashkou.hotels_booking.service.HotelService;
import com.chuyashkou.hotels_booking.service.impl.HotelServiceImpl;
import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

public class CancelHotelRegistrationCommandImpl implements Command {
    private static final String ID = "id";
    private static final String HOTELS = "hotels";
    private static final String HOTEL_CANCEL_REGISTRATION_ERROR = "cancelRegistrationError";
    private static final boolean ERROR_FLAG = true;

    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult(PageManager.getPageURI(PageMappingConstant.CONFIRM_HOTELS_REGISTRATION_PAGE.getKey()),
                NavigationType.FORWARD);
        HotelService hotelService = HotelServiceImpl.getInstance();
        try {
            Optional<Hotel> optionalHotel = hotelService.findById(Long.parseLong(req.getParameter(ID)));
            Optional<Hotel> updateHotel = Optional.empty();
            if (optionalHotel.isPresent()) {
                optionalHotel.get().setRegistered(false);
                updateHotel = hotelService.update(optionalHotel.get());
            } else {
                req.setAttribute(HOTEL_CANCEL_REGISTRATION_ERROR, ERROR_FLAG);
            }
            if (updateHotel.isEmpty()) {
                req.setAttribute(HOTEL_CANCEL_REGISTRATION_ERROR, ERROR_FLAG);
            }
            Map<Long, Hotel> hotels = hotelService.findAll();
            req.setAttribute(HOTELS, hotels);
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }
}
