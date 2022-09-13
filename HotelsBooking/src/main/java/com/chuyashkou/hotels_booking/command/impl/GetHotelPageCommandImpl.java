package com.chuyashkou.hotels_booking.command.impl;

import com.chuyashkou.hotels_booking.command.Command;
import com.chuyashkou.hotels_booking.command.CommandResult;
import com.chuyashkou.hotels_booking.command.NavigationType;
import com.chuyashkou.hotels_booking.exception.CommandException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.Apartment;
import com.chuyashkou.hotels_booking.model.ApartmentSearchData;
import com.chuyashkou.hotels_booking.model.Hotel;
import com.chuyashkou.hotels_booking.service.HotelService;
import com.chuyashkou.hotels_booking.service.impl.HotelServiceImpl;
import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class GetHotelPageCommandImpl implements Command {

    private static final String ID = "id";
    private static final String HOTEL = "hotel";
    private static final String TOTAL_PRICES = "totalPrices";
    private static final String NIGHTS_COUNT = "nightsCount";
    private static final String DATE_IN = "dateIn";
    private static final String DATE_OUT = "dateOut";
    private static final String MIN_RANGE_HOTEL_PRICE = "minRangeHotelPrice";
    private static final String MAX_RANGE_HOTEL_PRICE = "maxRangeHotelPrice";
    private static final String MIN_HOTEL_PRICE = "minHotelPrice";
    private static final String MAX_HOTEL_PRICE = "maxHotelPrice";
    private static final String INPUT_MIN_PRICE = "inputMinPrice";
    private static final String INPUT_MAX_PRICE = "inputMaxPrice";
    private static final String CATEGORY = "category";
    private static final String APARTMENT_SEARCH_DATA = "apartmentSearchData";

    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult(PageManager.getPageURI(PageMappingConstant.HOTEL_PAGE.getKey()),
                NavigationType.FORWARD);
        HotelService hotelService = HotelServiceImpl.getInstance();
        try {
            double minHotelPrice = Double.parseDouble(req.getParameter(MIN_HOTEL_PRICE));
            double maxHotelPrice = Double.parseDouble(req.getParameter(MAX_HOTEL_PRICE));
            long inputMinHotelPrice = this.getInputMinHotelPrice(req, minHotelPrice);
            long inputMaxHotelPrice = this.getInputMaxHotelPrice(req, maxHotelPrice);
            ApartmentSearchData apartmentSearchData = buildApartmentSearchData(req, inputMinHotelPrice, inputMaxHotelPrice);
            Optional<Hotel> hotel = hotelService.findHotelByIdAndApartmentSearchData(Long.parseLong(req.getParameter(ID)), apartmentSearchData);
            if (hotel.isPresent()) {
                long nightsCount = this.getNightsCount(req);
                Map<Long, Double> pricesMap = hotel.get().getApartments().values().stream().collect(Collectors
                        .toMap(Apartment::getId, apartment -> apartment.getPrice() * nightsCount));
                req.setAttribute(MAX_HOTEL_PRICE, maxHotelPrice);
                req.setAttribute(MIN_HOTEL_PRICE, minHotelPrice);
                req.setAttribute(MIN_RANGE_HOTEL_PRICE, Math.round(Math.floor(minHotelPrice)));
                req.setAttribute(MAX_RANGE_HOTEL_PRICE, Math.round(Math.ceil(maxHotelPrice)));
                req.setAttribute(APARTMENT_SEARCH_DATA, apartmentSearchData);
                req.setAttribute(TOTAL_PRICES, pricesMap);
                req.setAttribute(NIGHTS_COUNT, nightsCount);
                req.setAttribute(HOTEL, hotel.get());
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }

    private long getInputMaxHotelPrice(HttpServletRequest req, double maxHotelPrice) {
        String inputMaxHotelPrice = req.getParameter(INPUT_MAX_PRICE);
        if (Objects.isNull(inputMaxHotelPrice)) {
            return Math.round(Math.ceil(maxHotelPrice));
        } else {
            return Math.round(Math.ceil(Double.parseDouble(inputMaxHotelPrice)));
        }
    }

    private long getInputMinHotelPrice(HttpServletRequest req, double minHotelPrice) {
        String inputMinHotelPrice = req.getParameter(INPUT_MIN_PRICE);
        if (Objects.isNull(inputMinHotelPrice)) {
            return Math.round(Math.floor(minHotelPrice));
        } else {
            return Math.round(Math.floor(Double.parseDouble(inputMinHotelPrice)));
        }
    }

    private long getNightsCount(HttpServletRequest req) {
        LocalDate dateIn = LocalDate.parse(req.getParameter(DATE_IN));
        LocalDate dateOut = LocalDate.parse(req.getParameter(DATE_OUT));
        return dateIn.until(dateOut, ChronoUnit.DAYS);
    }

    private ApartmentSearchData buildApartmentSearchData(HttpServletRequest req, long minHotelPrice, long maxHotelPrice) {
        return ApartmentSearchData.builder()
                .minHotelPrice(minHotelPrice)
                .maxHotelPrice(maxHotelPrice)
                .dateIn(LocalDate.parse(req.getParameter(DATE_IN)))
                .dateOut(LocalDate.parse(req.getParameter(DATE_OUT)))
                .category(req.getParameter(CATEGORY))
                .build();
    }
}
