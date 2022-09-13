package com.chuyashkou.hotels_booking.command.impl;

import com.chuyashkou.hotels_booking.command.Command;
import com.chuyashkou.hotels_booking.command.CommandResult;
import com.chuyashkou.hotels_booking.command.NavigationType;
import com.chuyashkou.hotels_booking.exception.CommandException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.Booking;
import com.chuyashkou.hotels_booking.model.User;
import com.chuyashkou.hotels_booking.service.BookingService;
import com.chuyashkou.hotels_booking.service.impl.BookingServiceImpl;
import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class GetUserBookingsPageCommandImpl implements Command {
    private static final String BOOKINGS = "bookings";
    private static final String USER = "user";

    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult(PageManager.getPageURI(PageMappingConstant.USER_BOOKINGS_PAGE.getKey()),
                NavigationType.FORWARD);
        BookingService bookingService = BookingServiceImpl.getInstance();
        User user = (User) req.getSession().getAttribute(USER);
        try {
            Map<Long, Booking> bookings = bookingService.findBookingsByUserId(user.getId());
            req.setAttribute(BOOKINGS, bookings);
        } catch (
                ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }
}



