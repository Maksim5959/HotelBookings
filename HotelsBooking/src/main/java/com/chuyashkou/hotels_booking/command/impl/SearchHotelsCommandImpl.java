package com.chuyashkou.hotels_booking.command.impl;

import com.chuyashkou.hotels_booking.command.Command;
import com.chuyashkou.hotels_booking.command.CommandResult;
import com.chuyashkou.hotels_booking.command.NavigationType;
import com.chuyashkou.hotels_booking.exception.CommandException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.Apartment;
import com.chuyashkou.hotels_booking.model.Hotel;
import com.chuyashkou.hotels_booking.model.HotelsFilter;
import com.chuyashkou.hotels_booking.model.HotelsSearchData;
import com.chuyashkou.hotels_booking.service.HotelService;
import com.chuyashkou.hotels_booking.service.impl.HotelServiceImpl;
import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchHotelsCommandImpl implements Command {

    private static final String CITY = "city";
    private static final String DATE_IN = "dateIn";
    private static final String DATE_OUT = "dateOut";
    private static final String ADULTS = "adults";
    private static final String CHILDREN = "children";
    private static final String ROOMS = "rooms";
    private static final String HOTELS_FILTER = "hotelsFilter";
    private static final String HOTELS = "hotels";
    private static final String HOTELS_SEARCH_DATA = "hotelsSearchData";
    private static final String MIN_HOTELS_PRICES = "minHotelsPrices";
    private static final String MAX_HOTELS_PRICES = "maxHotelsPrices";

    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        CommandResult commandResult = new CommandResult(PageManager.getPageURI(PageMappingConstant.SEARCH_PAGE.getKey()), NavigationType.FORWARD);
        HotelService hotelService = HotelServiceImpl.getInstance();
        try {
            HotelsSearchData hotelsSearchData = this.buildHotelSearchData(req);
            Map<Long, Hotel> hotels = hotelService.findHotelsByHotelSearchData(hotelsSearchData);
            req.setAttribute(HOTELS_SEARCH_DATA, hotelsSearchData);
            if (!hotels.isEmpty()) {
                Map<Long, Double> minHotelPrices = this.getMinHotelPrices(hotels);
                Map<Long, Double> maxHotelPrices = this.getMaxHotelPrices(hotels);
                req.setAttribute(MAX_HOTELS_PRICES, maxHotelPrices);
                req.setAttribute(MIN_HOTELS_PRICES, minHotelPrices);
                req.setAttribute(HOTELS, hotels);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessage.COMMAND_EXCEPTION_MESSAGE, e);
        }
        return commandResult;
    }

    private HotelsSearchData buildHotelSearchData(HttpServletRequest req) {
        Optional<String> hotelsFilter = Optional.ofNullable(req.getParameter(HOTELS_FILTER));
        String defaultFilterName = "default";
        return HotelsSearchData.builder()
                .city(req.getParameter(CITY))
                .dateIn(LocalDate.parse(req.getParameter(DATE_IN)))
                .dateOut(LocalDate.parse(req.getParameter(DATE_OUT)))
                .adultsCount(Integer.parseInt(req.getParameter(ADULTS)))
                .childrenCount(Integer.parseInt(req.getParameter(CHILDREN)))
                .roomsCount(Integer.parseInt(req.getParameter(ROOMS)))
                .hotelsFilter(HotelsFilter.valueOf(hotelsFilter.orElse(defaultFilterName).toUpperCase(Locale.ROOT)))
                .build();
    }

    private Map<Long, Double> getMinHotelPrices(Map<Long, Hotel> hotels) {
        return hotels.values().stream()
                .collect(Collectors.toMap(Hotel::getId, hotel -> hotel.getApartments().values().stream()
                        .mapToDouble(Apartment::getPrice)
                        .min()
                        .orElse(0)));
    }

    private Map<Long, Double> getMaxHotelPrices(Map<Long, Hotel> hotels) {
        return hotels.values().stream()
                .collect(Collectors.toMap(Hotel::getId, hotel -> hotel.getApartments().values().stream()
                        .mapToDouble(Apartment::getPrice)
                        .max()
                        .orElse(0)));
    }
}
