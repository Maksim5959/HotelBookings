package com.chuyashkou.hotels_booking.command.impl;

import com.chuyashkou.hotels_booking.command.Command;
import com.chuyashkou.hotels_booking.command.CommandResult;
import com.chuyashkou.hotels_booking.command.NavigationType;
import com.chuyashkou.hotels_booking.exception.CommandException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.Apartment;
import com.chuyashkou.hotels_booking.model.Booking;
import com.chuyashkou.hotels_booking.model.Hotel;
import com.chuyashkou.hotels_booking.model.User;
import com.chuyashkou.hotels_booking.service.BookingService;
import com.chuyashkou.hotels_booking.service.HotelService;
import com.chuyashkou.hotels_booking.service.impl.BookingServiceImpl;
import com.chuyashkou.hotels_booking.service.impl.HotelServiceImpl;
import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public class ConfirmBookingCommandImpl implements Command {
    private static final String APARTMENT_ID = "apartmentId";
    private static final String CHECK_IN_DATE = "checkInDate";
    private static final String CHECK_OUT_DATE = "checkOutDate";
    private static final String USER = "user";
    private static final String HOTEL = "hotel";
    private static final String BOOKINGS = "bookings";
    private static final String BOOKING_ID = "bookingId";
    private static final String CONFIRM_ERROR = "confirmError";
    private static final boolean ERROR_FLAG = true;


    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult(PageManager.getPageURI(PageMappingConstant.HOTEL_MANAGER_BOOKINGS_PAGE.getKey()),
                NavigationType.FORWARD);
        HotelService hotelService = HotelServiceImpl.getInstance();
        BookingService bookingService = BookingServiceImpl.getInstance();
        Booking booking = this.buildBooking(req);
        User user = (User) req.getSession().getAttribute(USER);
        try {
            Map<Long, Booking> bookingMap = bookingService.findBookingsByBookingData(booking);
            bookingMap.values().removeIf(b -> b.getId() == Long.parseLong(req.getParameter(BOOKING_ID)));
            Optional<Hotel> hotel = hotelService.findHotelByUserId(user.getId());
            Optional<Booking> optionalBooking = Optional.empty();
            if (bookingMap.isEmpty() && hotel.isPresent()) {
                optionalBooking = bookingService.update(booking);
            } else {
                req.setAttribute(CONFIRM_ERROR, ERROR_FLAG);
            }
            if (optionalBooking.isPresent()) {
                req.setAttribute(HOTEL, hotel.get());
                req.setAttribute(BOOKINGS, bookingService.findBookingsByHotelId(hotel.get().getId()));
            } else {
                req.setAttribute(CONFIRM_ERROR, ERROR_FLAG);
            }

        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }

    private Booking buildBooking(HttpServletRequest req) {
        return Booking.builder()
                .id(Long.parseLong(req.getParameter(BOOKING_ID)))
                .confirmTime(LocalDateTime.now())
                .dateIn(LocalDate.parse(req.getParameter(CHECK_IN_DATE)))
                .dateOut(LocalDate.parse(req.getParameter(CHECK_OUT_DATE)))
                .apartment(Apartment.builder()
                        .id(Long.parseLong(req.getParameter(APARTMENT_ID)))
                        .build())
                .build();
    }
}
