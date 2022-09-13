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
import java.util.Objects;
import java.util.Optional;

public class BookingRoomCommandImpl implements Command {
    private static final String TOTAL_PRICE = "totalPrice";
    private static final String HOTEL_ID = "hotelId";
    private static final String APARTMENT_ID = "apartmentId";
    private static final String CHECK_IN_DATE = "checkInDate";
    private static final String CHECK_OUT_DATE = "checkOutDate";
    private static final String BOOKINGS = "bookings";
    private static final String USER = "user";
    private static final String BOOKING_ERROR = "bookingError";
    private static final boolean ERROR_FLAG = true;

    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult(PageManager.getPageURI(PageMappingConstant.BOOKING_PAGE.getKey()),
                NavigationType.FORWARD);
        HotelService hotelService = HotelServiceImpl.getInstance();
        BookingService bookingService = BookingServiceImpl.getInstance();
        User user = (User) req.getSession().getAttribute(USER);
        Booking booking = this.buildBooking(req);
        Long hotelId = Long.parseLong(req.getParameter(HOTEL_ID));
        try {
            Optional<Hotel> optionalHotel = hotelService.findById(hotelId);
            Map<Long, Booking> bookingMap = bookingService.findBookingsByBookingData(booking);
            Optional<Hotel> hotel = Optional.empty();
            Long apartmentId = Long.parseLong(req.getParameter(APARTMENT_ID));
            Long bookingId = null;
            if (optionalHotel.isPresent() && bookingMap.isEmpty()) {
                hotel = hotelService.findById(hotelId);
            } else {
                req.setAttribute(BOOKING_ERROR, ERROR_FLAG);
            }

            if (hotel.isPresent() && hotel.get().getApartments().containsKey(apartmentId)) {
                Apartment apartment = hotel.get().getApartments().get(apartmentId);
                booking.setApartment(apartment);
                booking.setUser(user);
                booking.setTotalPrice(Double.parseDouble(req.getParameter(TOTAL_PRICE)));
                booking.setAddTime(LocalDateTime.now());
                bookingId = bookingService.create(booking);
            } else {
                req.setAttribute(BOOKING_ERROR, ERROR_FLAG);
            }

            if (Objects.nonNull(bookingId)) {
                Map<Long, Booking> bookings = bookingService.findBookingsByUserId(user.getId());
                req.setAttribute(TOTAL_PRICE, req.getParameter(TOTAL_PRICE));
                req.setAttribute(BOOKINGS, bookings);
                commandResult.setPage(PageManager.getPageURI(PageMappingConstant.USER_BOOKINGS_PAGE.getKey()));
                commandResult.setNavigationType(NavigationType.FORWARD);
            } else {
                req.setAttribute(BOOKING_ERROR, ERROR_FLAG);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }

    private Booking buildBooking(HttpServletRequest req) {
        return Booking.builder()
                .dateIn(LocalDate.parse(req.getParameter(CHECK_IN_DATE)))
                .dateOut(LocalDate.parse(req.getParameter(CHECK_OUT_DATE)))
                .apartment(Apartment.builder()
                        .id(Long.parseLong(req.getParameter(APARTMENT_ID)))
                        .build())
                .build();
    }
}
