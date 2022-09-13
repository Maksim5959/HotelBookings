package com.chuyashkou.hotels_booking.command.impl;

import com.chuyashkou.hotels_booking.command.Command;
import com.chuyashkou.hotels_booking.command.CommandResult;
import com.chuyashkou.hotels_booking.command.NavigationType;
import com.chuyashkou.hotels_booking.exception.CommandException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.Hotel;
import com.chuyashkou.hotels_booking.model.User;
import com.chuyashkou.hotels_booking.service.ApartmentService;
import com.chuyashkou.hotels_booking.service.HotelService;
import com.chuyashkou.hotels_booking.service.impl.ApartmentServiceImpl;
import com.chuyashkou.hotels_booking.service.impl.HotelServiceImpl;
import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class DeleteRoomCommandImpl implements Command {
    private static final String HOTEL = "hotel";
    private static final String ROOM_ID = "roomId";
    private static final String USER = "user";
    private static final String DELETE_ROOM_MESSAGE = "deleteRoomError";
    private static final boolean MESSAGE_FLAG = true;


    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult(PageManager.getPageURI(PageMappingConstant.HOTEL_MANAGER_ALL_ROOMS_PAGE.getKey()),
                NavigationType.FORWARD);
        HotelService hotelService = HotelServiceImpl.getInstance();
        ApartmentService apartmentService = ApartmentServiceImpl.getInstance();
        User user = (User) req.getSession().getAttribute(USER);
        try {
            boolean isDeleted = apartmentService.delete(Long.parseLong(req.getParameter(ROOM_ID)));
            Optional<Hotel> optionalHotel = hotelService.findHotelByUserId(user.getId());
            if (isDeleted && optionalHotel.isPresent()) {
                req.setAttribute(HOTEL, optionalHotel.get());
            } else {
                req.setAttribute(DELETE_ROOM_MESSAGE, MESSAGE_FLAG);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }
}
