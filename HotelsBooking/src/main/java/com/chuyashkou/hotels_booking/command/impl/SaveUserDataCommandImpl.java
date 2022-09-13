package com.chuyashkou.hotels_booking.command.impl;

import com.chuyashkou.hotels_booking.command.Command;
import com.chuyashkou.hotels_booking.command.CommandResult;
import com.chuyashkou.hotels_booking.command.NavigationType;
import com.chuyashkou.hotels_booking.exception.CommandException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.Gender;
import com.chuyashkou.hotels_booking.model.Role;
import com.chuyashkou.hotels_booking.model.User;
import com.chuyashkou.hotels_booking.service.UserService;
import com.chuyashkou.hotels_booking.service.impl.UserServiceImpl;
import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;
import com.chuyashkou.hotels_booking.util.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Optional;

public class SaveUserDataCommandImpl implements Command {
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String GENDER = "gender";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String USER = "user";
    private static final String SAVE_ERROR_NAME = "saveError";
    private static final boolean ERROR_FLAG = true;

    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult(PageManager.getPageURI(PageMappingConstant.USER_PERSONAL_DETAILS_PAGE.getKey()),
                NavigationType.FORWARD);
        UserService userService = UserServiceImpl.getInstance();
        User sessionUser = (User) req.getSession().getAttribute(USER);
        User user = buildUser(req);
        Optional<User> updateUser = Optional.empty();
        try {
            if (userService.findByEmailExcludingId(user.getEmail(), user.getId()).isEmpty()) {
                updateUser = userService.update(user);
            }
            if (updateUser.isPresent()) {
                updateUser.get().setAddress(sessionUser.getAddress());
                req.getSession().setAttribute(USER, updateUser.get());
            } else {
                req.setAttribute(SAVE_ERROR_NAME, ERROR_FLAG);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }

    private User buildUser(HttpServletRequest req) {
        return User.builder()
                .id(Long.parseLong(req.getParameter(ID)))
                .email(req.getParameter(EMAIL))
                .password(PasswordEncoder.encodePassword(req.getParameter(PASSWORD)))
                .firstName(req.getParameter(FIRST_NAME))
                .lastName(req.getParameter(LAST_NAME))
                .gender(Gender.valueOf(req.getParameter(GENDER)))
                .phoneNumber(req.getParameter(PHONE_NUMBER))
                .dateOfBirth(LocalDate.parse(req.getParameter(DATE_OF_BIRTH)))
                .role(Role.USER)
                .build();
    }
}
