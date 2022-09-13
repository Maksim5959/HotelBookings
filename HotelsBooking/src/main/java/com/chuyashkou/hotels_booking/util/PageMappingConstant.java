package com.chuyashkou.hotels_booking.util;

public enum PageMappingConstant {

    MAIN_PAGE("page.main"),
    REGISTRATION_PAGE("page.registration"),
    LOGIN_PAGE("page.login"),
    SEARCH_PAGE("page.search.hotels"),
    HOTEL_PAGE("page.hotel"),
    BOOKING_PAGE("page.booking"),
    PROFILE_PAGE("page.profile"),
    WAITING_PAGE("page.waiting"),
    REGISTRATION_HOTEL_PAGE("page.hotel.registration"),
    USER_PERSONAL_DETAILS_PAGE("page.user.personal.details"),
    USER_LOCAL_DATA_PAGE("page.user.local.data"),
    USER_SECURITY_PAGE("page.user.security"),
    HOTEL_MANAGER_UPDATE_PAGE("page.hotel.manager.update"),
    HOTEL_MANAGER_ALL_ROOMS_PAGE("page.hotel.manager.all.rooms"),
    HOTEL_MANAGER_BOOKINGS_PAGE("page.hotel.manager.bookings"),
    HOTEL_MANAGER_ADD_NEW_ROOM_PAGE("page.hotel.manager.add.new.room"),
    ADMIN_ALL_USERS_PAGE("page.admin.all.users"),
    ADMIN_ALL_HOTELS_PAGE("page.admin.all.hotels"),
    ADD_ADMIN_PAGE("page.add.admin"),
    CONFIRM_HOTELS_REGISTRATION_PAGE("page.confirm.hotels.registration"),
    USER_BOOKINGS_PAGE("page.user.bookings");

    private final String key;

    PageMappingConstant(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
