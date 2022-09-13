package com.chuyashkou.hotels_booking.command.impl;

import com.chuyashkou.hotels_booking.command.Command;
import com.chuyashkou.hotels_booking.command.CommandResult;
import com.chuyashkou.hotels_booking.command.NavigationType;
import com.chuyashkou.hotels_booking.exception.CommandException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.Apartment;
import com.chuyashkou.hotels_booking.model.Category;
import com.chuyashkou.hotels_booking.model.Comfort;
import com.chuyashkou.hotels_booking.service.ApartmentService;
import com.chuyashkou.hotels_booking.service.impl.ApartmentServiceImpl;
import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class AddRoomCommandImpl implements Command {
    private static final String HOTEL_ID = "hotelId";
    private static final String ROOM_NAME = "roomName";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";
    private static final String SINGLE_BED_COUNT = "singleBedCount";
    private static final String DOUBLE_BED_COUNT = "doubleBedCount";
    private static final String TV = "tv";
    private static final String CONDITIONER = "conditioner";
    private static final String BAR = "bar";
    private static final String REFRIGERATOR = "refrigerator";
    private static final String BALCONY = "balcony";
    private static final String JACUZZI = "jacuzzi";
    private static final String BREAKFAST = "breakfast";
    private static final String WIFI = "wifi";
    private static final String TRANSFER = "transfer";
    private static final String PARKING = "parking";
    private static final String SWIMMING_POOL = "swimmingPool";
    private static final String GYM = "gym";
    private static final String RESTAURANT = "restaurant";
    private static final String PETS = "pets";
    private static final String ACCESSIBILITY_FEATURES = "accessibilityFeatures";
    private static final String ADD_ERROR_MESSAGE = "addRoomError";
    private static final String ADD_SUCCESS_MESSAGE = "addSuccess";
    private static final boolean MESSAGE_FLAG = true;


    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult(PageManager.getPageURI(PageMappingConstant.HOTEL_MANAGER_ADD_NEW_ROOM_PAGE.getKey()),
                NavigationType.FORWARD);
        ApartmentService apartmentService = ApartmentServiceImpl.getInstance();
        Apartment apartment = buildApartment(req);
        Long apartmentId;
        try {
            apartmentId = apartmentService.create(apartment);
            if (Objects.nonNull(apartmentId)) {
                req.setAttribute(ADD_SUCCESS_MESSAGE, MESSAGE_FLAG);
            } else {
                req.setAttribute(ADD_ERROR_MESSAGE, MESSAGE_FLAG);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }

    private Apartment buildApartment(HttpServletRequest req) {
        return Apartment.builder()
                .hotelId(Long.parseLong(req.getParameter(HOTEL_ID)))
                .name(req.getParameter(ROOM_NAME))
                .price(Double.parseDouble(req.getParameter(PRICE)))
                .singleBedCount(Integer.parseInt(req.getParameter(SINGLE_BED_COUNT)))
                .doubleBedCount(Integer.parseInt(req.getParameter(DOUBLE_BED_COUNT)))
                .category(Category.values()[Integer.parseInt(req.getParameter(CATEGORY))])
                .comfort(Comfort.builder()
                        .tv(Objects.nonNull(req.getParameter(TV)))
                        .conditioner(Objects.nonNull(req.getParameter(CONDITIONER)))
                        .bar(Objects.nonNull(req.getParameter(BAR)))
                        .refrigerator(Objects.nonNull(req.getParameter(REFRIGERATOR)))
                        .balcony(Objects.nonNull(req.getParameter(BALCONY)))
                        .jacuzzi(Objects.nonNull(req.getParameter(JACUZZI)))
                        .breakfast(Objects.nonNull(req.getParameter(BREAKFAST)))
                        .wifi(Objects.nonNull(req.getParameter(WIFI)))
                        .transfer(Objects.nonNull(req.getParameter(TRANSFER)))
                        .parking(Objects.nonNull(req.getParameter(PARKING)))
                        .swimmingPool(Objects.nonNull(req.getParameter(SWIMMING_POOL)))
                        .gym(Objects.nonNull(req.getParameter(GYM)))
                        .restaurant(Objects.nonNull(req.getParameter(RESTAURANT)))
                        .pets(Objects.nonNull(req.getParameter(PETS)))
                        .accessibilityFeatures(Objects.nonNull(req.getParameter(ACCESSIBILITY_FEATURES)))
                        .build())
                .build();
    }
}
