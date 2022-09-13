package com.chuyashkou.hotels_booking.command.impl;

import com.chuyashkou.hotels_booking.command.Command;
import com.chuyashkou.hotels_booking.command.CommandResult;
import com.chuyashkou.hotels_booking.command.NavigationType;
import com.chuyashkou.hotels_booking.exception.CommandException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.Hotel;
import com.chuyashkou.hotels_booking.model.User;
import com.chuyashkou.hotels_booking.service.HotelService;
import com.chuyashkou.hotels_booking.service.impl.HotelServiceImpl;
import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class GetHotelManagerUpdatePageCommandImpl implements Command {
    private static final String USER = "user";
    private static final String HOTEL = "hotel";

    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult();
        HotelService hotelService = HotelServiceImpl.getInstance();
        User user = (User) req.getSession().getAttribute(USER);
        try {
            Optional<Hotel> hotel = hotelService.findHotelByUserId(user.getId());
            if (hotel.isPresent() && hotel.get().isRegistered()) {
                req.setAttribute(HOTEL, hotel.get());
                commandResult.setPage(PageManager.getPageURI(PageMappingConstant.HOTEL_MANAGER_UPDATE_PAGE.getKey()));
            } else {
                commandResult.setPage(PageManager.getPageURI(PageMappingConstant.WAITING_PAGE.getKey()));
            }
            commandResult.setNavigationType(NavigationType.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }
}
