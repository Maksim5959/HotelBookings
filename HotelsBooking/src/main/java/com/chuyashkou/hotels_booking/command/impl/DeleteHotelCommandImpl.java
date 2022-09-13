package com.chuyashkou.hotels_booking.command.impl;

import com.chuyashkou.hotels_booking.command.Command;
import com.chuyashkou.hotels_booking.command.CommandResult;
import com.chuyashkou.hotels_booking.command.NavigationType;
import com.chuyashkou.hotels_booking.exception.CommandException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.Role;
import com.chuyashkou.hotels_booking.model.User;
import com.chuyashkou.hotels_booking.service.HotelService;
import com.chuyashkou.hotels_booking.service.impl.HotelServiceImpl;
import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;

import javax.servlet.http.HttpServletRequest;

public class DeleteHotelCommandImpl implements Command {
    private static final String HOTEL_ID = "hotelId";
    private static final String USER = "user";
    private static final String DELETE_HOTEL_ERROR_NAME = "deleteHotelError";
    private static final boolean ERROR_FLAG = true;

    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult();
        HotelService hotelService = HotelServiceImpl.getInstance();
        User user = (User) req.getSession().getAttribute(USER);
        try {
            boolean isDeleted = hotelService.delete(Long.parseLong(req.getParameter(HOTEL_ID)));
            if (isDeleted) {
                user.setRole(Role.USER);
                req.getSession().setAttribute(USER, user);
                commandResult.setPage(req.getContextPath() + PageManager.getPageURI(PageMappingConstant.MAIN_PAGE.getKey()));
                commandResult.setNavigationType(NavigationType.REDIRECT);
            } else {
                req.setAttribute(DELETE_HOTEL_ERROR_NAME, ERROR_FLAG);
                commandResult.setPage(PageManager.getPageURI(PageMappingConstant.HOTEL_MANAGER_UPDATE_PAGE.getKey()));
                commandResult.setNavigationType(NavigationType.FORWARD);
            }

        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }
}
