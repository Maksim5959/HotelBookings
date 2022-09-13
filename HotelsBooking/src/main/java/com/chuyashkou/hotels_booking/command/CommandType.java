package com.chuyashkou.hotels_booking.command;


import com.chuyashkou.hotels_booking.command.impl.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CommandType {

    REGISTER(new UserRegistrationCommandImpl()),
    LOGIN(new LoginCommandImpl()),
    LOGOUT(new LogoutCommandImpl()),
    SEARCH_HOTELS(new SearchHotelsCommandImpl()),
    GET_HOTEL_PAGE(new GetHotelPageCommandImpl()),
    GET_BOOKING_ROOM_PAGE(new GetBookingRoomPageCommandImpl()),
    REGISTER_HOTEL(new HotelRegistrationCommandImpl()),
    SAVE_USER_DATA(new SaveUserDataCommandImpl()),
    SAVE_USER_ADDRESS(new SaveUserAddressCommandImpl()),
    DELETE_USER(new DeleteUserCommandImpl()),
    DELETE_USER_ADDRESS(new DeleteAddressCommandImpl()),
    UPDATE_PASSWORD(new UpdatePasswordCommandImpl()),
    GET_HOTEL_MANAGER_UPDATE_PAGE(new GetHotelManagerUpdatePageCommandImpl()),
    GET_HOTEL_MANAGER_ALL_ROOMS_PAGE(new GetHotelManagerAllRoomsPageCommandImpl()),
    GET_HOTEL_MANAGER_BOOKINGS_PAGE(new GetHotelManagerBookingsPageCommandImpl()),
    GET_HOTEL_MANAGER_ADD_NEW_ROOM_PAGE(new GetHotelManagerAddNewRoomPageCommandImpl()),
    UPDATE_HOTEL_DATA(new UpdateHotelDataCommandImpl()),
    DELETE_HOTEL(new DeleteHotelCommandImpl()),
    UPDATE_ROOM(new UpdateRoomCommandImpl()),
    ADD_ROOM(new AddRoomCommandImpl()),
    DELETE_ROOM(new DeleteRoomCommandImpl()),
    GET_ADMIN_ALL_USERS_PAGE(new GetAdminAllUsersPageCommandImpl()),
    GET_ADMIN_ALL_HOTELS_PAGE(new GetAdminAllHotelsPageCommandImpl()),
    GET_ADD_ADMIN_PAGE(new GetAddAdminPageCommandImpl()),
    GET_ADMIN_CONFIRM_HOTEL_REGISTRATION_PAGE(new GetAdminConfirmHotelRegistrationPageCommandImpl()),
    ADMIN_DELETE_USER(new AdminDeleteUserCommandImpl()),
    ADMIN_DELETE_HOTEL(new AdminDeleteHotelCommandImpl()),
    CONFIRM_HOTEL_REGISTRATION(new ConfirmHotelRegistrationCommandImpl()),
    CANCEL_HOTEL_REGISTRATION(new CancelHotelRegistrationCommandImpl()),
    ADD_NEW_ADMIN(new AddNewAdminCommandImpl()),
    BOOKING_ROOM(new BookingRoomCommandImpl()),
    GET_USER_BOOKINGS_PAGE(new GetUserBookingsPageCommandImpl()),
    CONFIRM_BOOKING(new ConfirmBookingCommandImpl()),
    USER_DELETE_BOOKING(new UserDeleteBookingCommandImpl()),
    HOTEL_MANAGER_DELETE_BOOKING(new HotelManagerDeleteBookingCommandImpl());

    private final Command command;

    public Command getCommand() {
        return command;
    }
}
