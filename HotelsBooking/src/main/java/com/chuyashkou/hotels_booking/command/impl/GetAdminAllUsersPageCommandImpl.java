package com.chuyashkou.hotels_booking.command.impl;

import com.chuyashkou.hotels_booking.command.Command;
import com.chuyashkou.hotels_booking.command.CommandResult;
import com.chuyashkou.hotels_booking.command.NavigationType;
import com.chuyashkou.hotels_booking.exception.CommandException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.User;
import com.chuyashkou.hotels_booking.service.UserService;
import com.chuyashkou.hotels_booking.service.impl.UserServiceImpl;
import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class GetAdminAllUsersPageCommandImpl implements Command {
    private static final String USERS = "users";

    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult(PageManager.getPageURI(PageMappingConstant.ADMIN_ALL_USERS_PAGE.getKey()),
                NavigationType.FORWARD);
        UserService userService = UserServiceImpl.getInstance();
        try {
            Map<Long, User> users = userService.findAll();
            req.setAttribute(USERS, users);
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }
}
