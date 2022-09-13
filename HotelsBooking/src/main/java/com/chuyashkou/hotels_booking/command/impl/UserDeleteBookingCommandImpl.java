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
import java.time.LocalDateTime;
import java.util.Map;

public class UserDeleteBookingCommandImpl implements Command {
    private static final String BOOKING_ID = "bookingId";
    private static final String USER = "user";
    private static final String CONFIRM_TIME = "confirmTime";
    private static final String BOOKINGS = "bookings";
    private static final String DELETE_BOOKING_ERROR = "deleteBookingError";
    private static final boolean ERROR_FLAG = true;


    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult(PageManager.getPageURI(PageMappingConstant.USER_BOOKINGS_PAGE.getKey()),
                NavigationType.FORWARD);
        BookingService bookingService = BookingServiceImpl.getInstance();
        User user = (User) req.getSession().getAttribute(USER);
        try {
            boolean isDeleted = false;
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime confirmTime = LocalDateTime.parse(req.getParameter(CONFIRM_TIME));
            if (now.minusHours(24).isBefore(confirmTime)) {
                isDeleted = bookingService.delete(Long.parseLong(req.getParameter(BOOKING_ID)));
            } else {
                req.setAttribute(DELETE_BOOKING_ERROR, ERROR_FLAG);
            }
            if (isDeleted) {
                Map<Long, Booking> bookings = bookingService.findBookingsByUserId(user.getId());
                req.setAttribute(BOOKINGS, bookings);
            } else {
                req.setAttribute(DELETE_BOOKING_ERROR, ERROR_FLAG);
            }

        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }
}
