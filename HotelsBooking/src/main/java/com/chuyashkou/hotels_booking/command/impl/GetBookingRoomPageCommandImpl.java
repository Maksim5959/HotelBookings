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
import java.time.LocalDate;
import java.util.Optional;

public class GetBookingRoomPageCommandImpl implements Command {
    private static final String TOTAL_PRICE = "totalPrice";
    private static final String NIGHTS_COUNT = "nightsCount";
    private static final String HOTEL_ID = "hotelId";
    private static final String APARTMENT_ID = "apartmentId";
    private static final String CHECK_IN_DATE = "checkInDate";
    private static final String CHECK_OUT_DATE = "checkOutDate";
    private static final String APARTMENT = "apartment";
    private static final String HOTEL = "hotel";
    private static final String BOOKING_PAGE_ERROR = "bookingPageError";
    private static final boolean ERROR_FLAG = true;

    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult(PageManager.getPageURI(PageMappingConstant.BOOKING_PAGE.getKey()),
                NavigationType.FORWARD);
        HotelService hotelService = HotelServiceImpl.getInstance();
        try {
            Optional<Hotel> optionalHotel = hotelService.findById(Long.parseLong(req.getParameter(HOTEL_ID)));
            Long apartmentId = Long.parseLong(req.getParameter(APARTMENT_ID));
            if (optionalHotel.isPresent() && optionalHotel.get().getApartments().containsKey(apartmentId)) {
                req.setAttribute(HOTEL, optionalHotel.get());
                req.setAttribute(APARTMENT, optionalHotel.get().getApartments().get(apartmentId));
                req.setAttribute(NIGHTS_COUNT, req.getParameter(NIGHTS_COUNT));
                req.setAttribute(CHECK_IN_DATE, LocalDate.parse(req.getParameter(CHECK_IN_DATE)));
                req.setAttribute(CHECK_OUT_DATE, LocalDate.parse(req.getParameter(CHECK_OUT_DATE)));
                req.setAttribute(TOTAL_PRICE, req.getParameter(TOTAL_PRICE));
            } else {
                req.setAttribute(BOOKING_PAGE_ERROR, ERROR_FLAG);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }
}
