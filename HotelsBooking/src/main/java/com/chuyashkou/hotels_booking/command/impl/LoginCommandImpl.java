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
import java.util.Optional;

public class LoginCommandImpl implements Command {

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String LOGIN_ERROR_NAME = "loginError";
    private static final boolean ERROR_FLAG = true;

    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult();
        UserService userService = UserServiceImpl.getInstance();
        try {
            Optional<User> user = userService.findUserByEmailAndPassword(req.getParameter(EMAIL), PasswordEncoder.encodePassword(req.getParameter(PASSWORD)));
            if (user.isPresent()) {
                req.getSession().setAttribute(USER, user.get());
                commandResult.setPage(req.getContextPath() + PageManager.getPageURI(PageMappingConstant.MAIN_PAGE.getKey()));
                commandResult.setNavigationType(NavigationType.REDIRECT);
            } else {
                req.setAttribute(LOGIN_ERROR_NAME, ERROR_FLAG);
                commandResult.setPage(PageManager.getPageURI(PageMappingConstant.LOGIN_PAGE.getKey()));
                commandResult.setNavigationType(NavigationType.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }
}
