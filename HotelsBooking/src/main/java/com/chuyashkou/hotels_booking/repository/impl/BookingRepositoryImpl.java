package com.chuyashkou.hotels_booking.repository.impl;

import com.chuyashkou.hotels_booking.connection.ConnectionPool;
import com.chuyashkou.hotels_booking.connection.ProxyConnection;
import com.chuyashkou.hotels_booking.exception.ConnectionPoolException;
import com.chuyashkou.hotels_booking.exception.RepositoryException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.message.LoggerMessage;
import com.chuyashkou.hotels_booking.model.*;
import com.chuyashkou.hotels_booking.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class BookingRepositoryImpl implements BookingRepository {

    private static final String SQL_FIND_BOOKINGS_BY_HOTEL_ID = "SELECT * FROM bookings " +
            "JOIN users u on u.id = bookings.user_id " +
            "JOIN apartments a on a.id = bookings.apartment_id " +
            "JOIN hotels h on h.id = a.hotel_id " +
            "JOIN addresses a2 on h.id = a2.hotel_id " +
            "WHERE a.hotel_id = ?";
    private static final String SQL_FIND_BOOKINGS_BY_USER_ID = "SELECT * FROM bookings " +
            "JOIN users u on u.id = bookings.user_id " +
            "JOIN apartments a on a.id = bookings.apartment_id " +
            "JOIN hotels h on h.id = a.hotel_id " +
            "JOIN addresses a2 on h.id = a2.hotel_id " +
            "WHERE bookings.user_id = ?";
    private static final String SQL_FIND_ALL_BOOKINGS = "SELECT * FROM bookings " +
            "JOIN users u on u.id = bookings.user_id " +
            "JOIN apartments a on a.id = bookings.apartment_id " +
            "JOIN hotels h on h.id = a.hotel_id " +
            "JOIN addresses a2 on h.id = a2.hotel_id";
    private static final String SQL_ADD_BOOKING = "INSERT INTO " +
            "bookings (date_in, date_out, user_id, apartment_id,add_time,total_price) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE_BOOKING = "UPDATE bookings SET is_confirm = 1, confirm_time = ? WHERE id = ?";
    private static final String SQL_DELETE_BOOKING_BY_ID = "DELETE FROM bookings WHERE id = ?";
    private static final String SQL_GET_BOOKING_ID_BY_APARTMENT_ID = "SELECT * FROM bookings WHERE apartment_id = ?";

    private static final BookingRepositoryImpl INSTANCE = new BookingRepositoryImpl();
    private static final String ADD_TIME = "add_time";
    private static final String CONFIRM_TIME = "confirm_time";
    private static final String BOOKING_ID = "bookings.id";
    private static final String IS_CONFIRM = "is_confirm";
    private static final String DATE_IN = "date_in";
    private static final String DATE_OUT = "date_out";
    private static final String TOTAL_PRICE = "total_price";
    private static final String USER_ID = "u.id";
    private static final String EMAIL = "email";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String APARTMENT_ID = "a.id";
    private static final String APARTMENT_NAME = "apartment_name";
    private static final String LEGAL_NAME = "legal_name";
    private static final String BRAND = "brand";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String HOUSE = "house";
    private static final String BUILDING = "building";
    private static final String STARS = "stars";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private BookingRepositoryImpl() {
    }

    public static BookingRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Map<Long, Booking> findAll() throws RepositoryException {
        ProxyConnection connection = null;
        Statement statement = null;
        Map<Long, Booking> bookings = new LinkedHashMap<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_BOOKINGS);
            while (resultSet.next()) {
                Booking booking = this.buildBooking(resultSet);
                bookings.put(booking.getId(), booking);
            }
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(statement);
            this.releaseConnection(connection);
        }
        return bookings;
    }

    @Override
    public Long create(Booking booking) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        Long bookingId = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_ADD_BOOKING, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setDate(1, Date.valueOf(booking.getDateIn()));
            statement.setDate(2, Date.valueOf(booking.getDateOut()));
            statement.setLong(3, booking.getUser().getId());
            statement.setLong(4, booking.getApartment().getId());
            statement.setTimestamp(5, Timestamp.valueOf(booking.getAddTime()));
            statement.setDouble(6, booking.getTotalPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                bookingId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(statement);
            this.releaseConnection(connection);
        }
        return bookingId;
    }

    @Override
    public Optional<Booking> update(Booking booking) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        Optional<Booking> optionalBooking = Optional.empty();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_BOOKING);
            statement.setTimestamp(1, Timestamp.valueOf(booking.getConfirmTime()));
            statement.setLong(2, booking.getId());
            if (statement.executeUpdate() == 1) {
                optionalBooking = Optional.of(booking);
            }
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(statement);
            this.releaseConnection(connection);
        }
        return optionalBooking;
    }

    @Override
    public boolean delete(Long id) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_DELETE_BOOKING_BY_ID);
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(statement);
            this.releaseConnection(connection);
        }
    }

    @Override
    public Map<Long, Booking> findBookingsByUserId(Long id) throws RepositoryException {
        return this.findBookingsById(id, SQL_FIND_BOOKINGS_BY_USER_ID);
    }

    @Override
    public Map<Long, Booking> findBookingsByHotelId(Long id) throws RepositoryException {
        return this.findBookingsById(id, SQL_FIND_BOOKINGS_BY_HOTEL_ID);
    }

    @Override
    public Long findBookingIdByApartmentId(Long apartmentId) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        Long bookingId = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_GET_BOOKING_ID_BY_APARTMENT_ID);
            statement.setLong(1, apartmentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                bookingId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(statement);
            this.releaseConnection(connection);
        }
        return bookingId;
    }

    private Map<Long, Booking> findBookingsById(Long id, String sqlQuery) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        Map<Long, Booking> bookingMap = new LinkedHashMap<>();

        try {
            connection = ConnectionPool.getInstance().getConnection();

            statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Booking booking = this.buildBooking(resultSet);
                bookingMap.put(booking.getId(), booking);
            }
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(statement);
            this.releaseConnection(connection);
        }
        return bookingMap;
    }

    private Booking buildBooking(ResultSet resultSet) throws SQLException {
        Timestamp addTime = resultSet.getTimestamp(ADD_TIME);
        Timestamp confirmTime = resultSet.getTimestamp(CONFIRM_TIME);
        Booking booking = Booking.builder()
                .id(resultSet.getLong(BOOKING_ID))
                .isConfirm(resultSet.getBoolean(IS_CONFIRM))
                .dateIn(resultSet.getDate(DATE_IN).toLocalDate())
                .dateOut(resultSet.getDate(DATE_OUT).toLocalDate())
                .totalPrice(resultSet.getDouble(TOTAL_PRICE))
                .user(User.builder()
                        .id(resultSet.getLong(USER_ID))
                        .email(resultSet.getString(EMAIL))
                        .firstName(resultSet.getString(FIRST_NAME))
                        .lastName(resultSet.getString(LAST_NAME))
                        .phoneNumber(resultSet.getString(PHONE_NUMBER))
                        .build())
                .apartment(Apartment.builder()
                        .id(resultSet.getLong(APARTMENT_ID))
                        .name(resultSet.getString(APARTMENT_NAME))
                        .build())
                .hotel(Hotel.builder()
                        .legalName(resultSet.getString(LEGAL_NAME))
                        .brand(resultSet.getString(BRAND))
                        .address(Address.builder()
                                .country(resultSet.getString(COUNTRY))
                                .city(resultSet.getString(CITY))
                                .street(resultSet.getString(STREET))
                                .house(resultSet.getString(HOUSE))
                                .building(resultSet.getString(BUILDING))
                                .build())
                        .stars(Stars.values()[(resultSet.getInt(STARS))])
                        .build())
                .build();
        if (Objects.nonNull(addTime)) {
            booking.setAddTime(addTime.toLocalDateTime());
        }
        if (Objects.nonNull(confirmTime)) {
            booking.setConfirmTime(confirmTime.toLocalDateTime());
        }
        return booking;
    }

    private void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error(LoggerMessage.CANT_CLOSE_STATEMENT_MESSAGE, e);
        }
    }

    private void releaseConnection(ProxyConnection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
