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
import com.chuyashkou.hotels_booking.util.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;

public class UpdatePasswordCommandImpl implements Command {
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String UPDATE_PASSWORD_ERROR_MESSAGE = "updatePasswordError";
    private static final String SUCCESS_UPDATE_MESSAGE = "successUpdate";
    private static final boolean MESSAGE_FLAG = true;

    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult(PageManager.getPageURI(PageMappingConstant.USER_SECURITY_PAGE.getKey()),
                NavigationType.FORWARD);
        UserService userService = UserServiceImpl.getInstance();
        User user = (User) req.getSession().getAttribute(USER);
        try {
            user.setPassword(PasswordEncoder.encodePassword(req.getParameter(PASSWORD)));
            boolean isUpdated = userService.updateUserPassword(user);
            if (isUpdated) {
                req.setAttribute(SUCCESS_UPDATE_MESSAGE, MESSAGE_FLAG);
            } else {
                req.setAttribute(UPDATE_PASSWORD_ERROR_MESSAGE, MESSAGE_FLAG);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }
}
