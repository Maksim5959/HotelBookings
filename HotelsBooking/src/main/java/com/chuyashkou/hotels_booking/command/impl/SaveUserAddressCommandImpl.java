package com.chuyashkou.hotels_booking.command.impl;

import com.chuyashkou.hotels_booking.command.Command;
import com.chuyashkou.hotels_booking.command.CommandResult;
import com.chuyashkou.hotels_booking.command.NavigationType;
import com.chuyashkou.hotels_booking.exception.CommandException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.Address;
import com.chuyashkou.hotels_booking.model.User;
import com.chuyashkou.hotels_booking.service.UserService;
import com.chuyashkou.hotels_booking.service.impl.UserServiceImpl;
import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

public class SaveUserAddressCommandImpl implements Command {
    private static final String USER = "user";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String HOUSE = "house";
    private static final String BUILDING = "building";
    private static final String APARTMENT_NUMBER = "apartmentNumber";
    private static final String SAVE_ADDRESS_ERROR_NAME = "saveAddressError";
    private static final boolean ERROR_FLAG = true;


    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult();
        UserService userService = UserServiceImpl.getInstance();
        Address address = buildUserAddress(req);
        User user = (User) req.getSession().getAttribute(USER);
        Optional<User> userWithAddress;
        try {
            if (Objects.nonNull(user.getAddress().getCountry())) {
                address.setId(user.getAddress().getId());
                user.setAddress(address);
                userWithAddress = userService.updateUserAddress(user);
            } else {
                user.setAddress(address);
                userWithAddress = userService.createUserAddress(user);
            }
            if (userWithAddress.isPresent()) {
                req.getSession().setAttribute(USER, user);
                commandResult.setPage(req.getContextPath() + PageManager.getPageURI(PageMappingConstant.USER_LOCAL_DATA_PAGE.getKey()));
                commandResult.setNavigationType(NavigationType.REDIRECT);
            } else {
                req.getSession().setAttribute(SAVE_ADDRESS_ERROR_NAME, ERROR_FLAG);
                commandResult.setPage(PageManager.getPageURI(PageMappingConstant.USER_LOCAL_DATA_PAGE.getKey()));
                commandResult.setNavigationType(NavigationType.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }

    private Address buildUserAddress(HttpServletRequest req) {
        return Address.builder()
                .country(req.getParameter(COUNTRY))
                .city(req.getParameter(CITY))
                .street(req.getParameter(STREET))
                .house(req.getParameter(HOUSE))
                .building(req.getParameter(BUILDING))
                .apartmentNumber(req.getParameter(APARTMENT_NUMBER))
                .build();
    }
}
