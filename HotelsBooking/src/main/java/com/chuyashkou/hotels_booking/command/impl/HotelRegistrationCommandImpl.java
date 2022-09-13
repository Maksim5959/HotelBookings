package com.chuyashkou.hotels_booking.command.impl;

import com.chuyashkou.hotels_booking.command.Command;
import com.chuyashkou.hotels_booking.command.CommandResult;
import com.chuyashkou.hotels_booking.command.NavigationType;
import com.chuyashkou.hotels_booking.exception.CommandException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.*;
import com.chuyashkou.hotels_booking.service.HotelService;
import com.chuyashkou.hotels_booking.service.impl.HotelServiceImpl;
import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class HotelRegistrationCommandImpl implements Command {

    private static final String LEGAL_NAME = "legalName";
    private static final String BRAND = "brand";
    private static final String STARS = "stars";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String HOUSE = "house";
    private static final String BUILDING = "building";
    private static final String USER = "user";
    private static final String REGISTRATION_ERROR_NAME = "registrationError";
    private static final boolean ERROR_FLAG = true;

    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult();
        HotelService hotelService = HotelServiceImpl.getInstance();
        User user = (User) req.getSession().getAttribute("user");
        Hotel hotel = this.buildHotel(req, user);
        Long id = null;
        try {
            if (hotelService.findHotelByHotelData(hotel).isEmpty()) {
                id = hotelService.create(hotel);
            }
            if (Objects.nonNull(id)) {
                user.setRole(Role.MANAGER);
                req.getSession().setAttribute(USER, user);
                commandResult.setPage(req.getContextPath() + PageManager.getPageURI(PageMappingConstant.WAITING_PAGE.getKey()));
                commandResult.setNavigationType(NavigationType.REDIRECT);
            } else {
                req.setAttribute(REGISTRATION_ERROR_NAME, ERROR_FLAG);
                commandResult.setPage(PageManager.getPageURI(PageMappingConstant.REGISTRATION_HOTEL_PAGE.getKey()));
                commandResult.setNavigationType(NavigationType.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }

    private Hotel buildHotel(HttpServletRequest req, User user) {
        return Hotel.builder()
                .legalName(req.getParameter(LEGAL_NAME))
                .brand(req.getParameter(BRAND))
                .stars(Stars.values()[Integer.parseInt(req.getParameter(STARS))])
                .address(Address.builder()
                        .country(req.getParameter(COUNTRY))
                        .city(req.getParameter(CITY))
                        .street(req.getParameter(STREET))
                        .house(req.getParameter(HOUSE))
                        .building(req.getParameter(BUILDING))
                        .build())
                .managerId(user.getId())
                .build();
    }
}
