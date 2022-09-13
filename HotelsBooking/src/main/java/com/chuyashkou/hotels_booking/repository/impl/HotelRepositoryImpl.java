package com.chuyashkou.hotels_booking.repository.impl;

import com.chuyashkou.hotels_booking.connection.ConnectionPool;
import com.chuyashkou.hotels_booking.connection.ProxyConnection;
import com.chuyashkou.hotels_booking.exception.ConnectionPoolException;
import com.chuyashkou.hotels_booking.exception.RepositoryException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.message.LoggerMessage;
import com.chuyashkou.hotels_booking.model.*;
import com.chuyashkou.hotels_booking.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class HotelRepositoryImpl implements HotelRepository {

    private static final String SQL_FIND_HOTEL_BY_ID = "SELECT * FROM hotels " +
            "JOIN addresses a on hotels.id = a.hotel_id " +
            "LEFT JOIN apartments a2 on hotels.id = a2.hotel_id " +
            "LEFT JOIN comforts c on a2.id = c.apartment_id " +
            "LEFT JOIN bookings b on a2.id = b.apartment_id " +
            "WHERE hotels.id = ?";
    private static final String SQL_FIND_HOTELS_BY_SEARCH_DATA = "SELECT * FROM hotels " +
            "JOIN addresses a on hotels.id = a.hotel_id " +
            "JOIN apartments a2 on hotels.id = a2.hotel_id " +
            "JOIN comforts c on a2.id = c.apartment_id " +
            "LEFT JOIN bookings b on a2.id = b.apartment_id " +
            "WHERE city = ?";
    private static final String SQL_FIND_HOTEL_BY_HOTEL_DATA = "SELECT * FROM hotels  " +
            "JOIN addresses a on hotels.id = a.hotel_id WHERE legal_name = ?" +
            " OR (a.country = ? AND a.city = ? AND a.street = ? AND a.house = ? AND a.building = ?)";
    private static final String SQL_UPDATE_USER_ROLE_TO_USER = "UPDATE users " +
            "SET role = 0 WHERE users.id = (SELECT user_id FROM hotels WHERE hotels.id = ?)";
    private static final String SQL_UPDATE_USER_ROLE_TO_MANAGER = "UPDATE users SET role = 1 WHERE users.id = ?";
    private static final String SQL_CREATE_ADDRESS = "INSERT " +
            "INTO addresses (country ,city, street, house, building, hotel_id) VALUES (?,?,?,?,?,?)";
    private static final String SQL_CREATE_HOTEL = "INSERT INTO hotels (legal_name, brand, stars, user_id) VALUES(?,?,?,?)";
    private static final String SQL_FIND_HOTEL_BY_USER_ID = "SELECT * FROM hotels " +
            "JOIN addresses a on hotels.id = a.hotel_id " +
            "LEFT JOIN apartments a2 on hotels.id = a2.hotel_id " +
            "LEFT JOIN comforts c on a2.id = c.apartment_id " +
            "LEFT JOIN bookings b on a2.id = b.apartment_id " +
            "WHERE hotels.user_id = ?";
    private static final String SQL_UPDATE_HOTEL_DATA = "UPDATE hotels " +
            "SET legal_name = ?, brand = ?, stars = ?, is_registered = ? WHERE id = ?";
    private static final String SQL_UPDATE_HOTEL_ADDRESS_DATA = "UPDATE addresses " +
            "SET country = ?, city = ?, street = ?, house = ?, building = ? WHERE hotel_id =?";
    private static final String SQL_DELETE_HOTEL = "DELETE FROM hotels WHERE id = ?";
    private static final String SQL_FIND_ALL_HOTELS = "SELECT * FROM hotels " +
            "JOIN addresses a on hotels.id = a.hotel_id";

    private static final HotelRepositoryImpl INSTANCE = new HotelRepositoryImpl();
    private static final String BOOKING_ID = "b.id";
    private static final String IS_CONFIRM = "is_confirm";
    private static final String DATE_IN = "date_in";
    private static final String DATE_OUT = "date_out";
    private static final String APARTMENT_ID = "a2.id";
    private static final String APARTMENT_NAME = "apartment_name";
    private static final String LEGAL_NAME = "legal_name";
    private static final String BRAND = "brand";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String HOUSE = "house";
    private static final String BUILDING = "building";
    private static final String STARS = "stars";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";
    private static final String SINGLE_BED_COUNT = "single_bed_count";
    private static final String DOUBLE_BED_COUNT = "double_bed_count";
    private static final String COMFORT_ID = "c.id";
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
    private static final String SWIMMING_POOL = "swimming_pool";
    private static final String GYM = "gym";
    private static final String RESTAURANT = "restaurant";
    private static final String PETS = "pets";
    private static final String ACCESSIBILITY_FEATURES = "accessibility_features";
    private static final String HOTEL_ID = "hotels.id";
    private static final String ADDRESS_ID = "a.id";
    private static final String IS_REGISTERED = "is_registered";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private HotelRepositoryImpl() {
    }

    public static HotelRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Hotel> findHotelByHotelData(Hotel hotel) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        Optional<Hotel> optionalHotel = Optional.empty();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_FIND_HOTEL_BY_HOTEL_DATA);
            statement.setString(1, hotel.getLegalName());
            statement.setString(2, hotel.getAddress().getCountry());
            statement.setString(3, hotel.getAddress().getCity());
            statement.setString(4, hotel.getAddress().getStreet());
            statement.setString(5, hotel.getAddress().getHouse());
            statement.setString(6, hotel.getAddress().getBuilding());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                optionalHotel = Optional.ofNullable(this.buildHotel(resultSet));
            }
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(statement);
            this.releaseConnection(connection);
        }
        return optionalHotel;
    }

    @Override
    public Map<Long, Hotel> findHotelByHotelSearchData(HotelsSearchData hotelsSearchData) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        Map<Long, Hotel> hotels = new LinkedHashMap<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_FIND_HOTELS_BY_SEARCH_DATA);
            statement.setString(1, hotelsSearchData.getCity());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long hotelId = resultSet.getLong("hotels.id");
                Hotel hotel;
                if (hotels.containsKey(hotelId)) {
                    hotel = hotels.get(hotelId);
                } else {
                    hotel = this.buildHotel(resultSet);
                }
                this.addApartment(resultSet, hotel);
                this.addBooking(resultSet, hotel);
                hotels.put(hotel.getId(), hotel);
            }
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(statement);
            this.releaseConnection(connection);
        }
        return hotels;
    }

    @Override
    public Optional<Hotel> findHotelByUserId(Long userId) throws RepositoryException {
        return getHotel(userId, SQL_FIND_HOTEL_BY_USER_ID);
    }

    @Override
    public Map<Long, Hotel> findAll() throws RepositoryException {
        ProxyConnection connection = null;
        Statement statement = null;
        Map<Long, Hotel> hotels = new LinkedHashMap<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_HOTELS);
            while (resultSet.next()) {
                Hotel hotel = buildHotel(resultSet);
                hotels.put(hotel.getId(), hotel);
            }
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(statement);
            this.releaseConnection(connection);
        }
        return hotels;
    }

    @Override
    public Optional<Hotel> findById(Long id) throws RepositoryException {
        return getHotel(id, SQL_FIND_HOTEL_BY_ID);
    }

    @Override
    public Long create(Hotel hotel) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement updateUserRoleStatement = null;
        PreparedStatement createAddressStatement = null;
        PreparedStatement createHotelStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            updateUserRoleStatement = connection.prepareStatement(SQL_UPDATE_USER_ROLE_TO_MANAGER);
            updateUserRoleStatement.setLong(1, hotel.getManagerId());
            updateUserRoleStatement.executeUpdate();

            createHotelStatement = connection.prepareStatement(SQL_CREATE_HOTEL, PreparedStatement.RETURN_GENERATED_KEYS);
            createHotelStatement.setString(1, hotel.getLegalName());
            createHotelStatement.setString(2, hotel.getBrand());
            createHotelStatement.setInt(3, hotel.getStars().ordinal());
            createHotelStatement.setLong(4, hotel.getManagerId());
            createHotelStatement.executeUpdate();
            ResultSet generatedHotelKey = createHotelStatement.getGeneratedKeys();
            if (generatedHotelKey.next()) {
                hotel.setId(generatedHotelKey.getLong(1));
            }

            createAddressStatement = connection.prepareStatement(SQL_CREATE_ADDRESS, PreparedStatement.RETURN_GENERATED_KEYS);
            createAddressStatement.setString(1, hotel.getAddress().getCountry());
            createAddressStatement.setString(2, hotel.getAddress().getCity());
            createAddressStatement.setString(3, hotel.getAddress().getStreet());
            createAddressStatement.setString(4, hotel.getAddress().getHouse());
            createAddressStatement.setString(5, hotel.getAddress().getBuilding());
            createAddressStatement.setLong(6, hotel.getId());
            createAddressStatement.executeUpdate();
            ResultSet generatedAddressKey = createAddressStatement.getGeneratedKeys();
            if (generatedAddressKey.next()) {
                hotel.getAddress().setId(generatedAddressKey.getLong(1));
            }

            connection.commit();
        } catch (SQLException e) {
            rollbackTransaction(connection);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(updateUserRoleStatement);
            this.closeStatement(createAddressStatement);
            this.closeStatement(createHotelStatement);
            this.releaseConnection(connection);
        }
        return hotel.getId();
    }

    @Override
    public Optional<Hotel> update(Hotel hotel) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement updateHotelDataStatement = null;
        PreparedStatement updateAddressStatement = null;
        boolean hotelUpdated;
        boolean addressUpdated;
        Optional<Hotel> optionalHotel = Optional.empty();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            updateHotelDataStatement = connection.prepareStatement(SQL_UPDATE_HOTEL_DATA);
            updateHotelDataStatement.setString(1, hotel.getLegalName());
            updateHotelDataStatement.setString(2, hotel.getBrand());
            updateHotelDataStatement.setInt(3, hotel.getStars().ordinal());
            updateHotelDataStatement.setBoolean(4, hotel.isRegistered());
            updateHotelDataStatement.setLong(5, hotel.getId());

            hotelUpdated = updateHotelDataStatement.executeUpdate() == 1;

            updateAddressStatement = connection.prepareStatement(SQL_UPDATE_HOTEL_ADDRESS_DATA);
            updateAddressStatement.setString(1, hotel.getAddress().getCountry());
            updateAddressStatement.setString(2, hotel.getAddress().getCity());
            updateAddressStatement.setString(3, hotel.getAddress().getStreet());
            updateAddressStatement.setString(4, hotel.getAddress().getHouse());
            updateAddressStatement.setString(5, hotel.getAddress().getBuilding());
            updateAddressStatement.setLong(6, hotel.getId());
            addressUpdated = updateAddressStatement.executeUpdate() == 1;

            connection.commit();

            if (hotelUpdated && addressUpdated) optionalHotel = Optional.of(hotel);
        } catch (SQLException e) {
            rollbackTransaction(connection);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(updateHotelDataStatement);
            this.closeStatement(updateAddressStatement);
            this.releaseConnection(connection);
        }
        return optionalHotel;
    }

    @Override
    public boolean delete(Long id) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement deleteHotelStatement = null;
        PreparedStatement updateUserRoleStatement = null;
        boolean hotelDeleted = false;
        boolean userRoleUpdated = false;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            updateUserRoleStatement = connection.prepareStatement(SQL_UPDATE_USER_ROLE_TO_USER);
            updateUserRoleStatement.setLong(1, id);
            userRoleUpdated = updateUserRoleStatement.executeUpdate() == 1;

            deleteHotelStatement = connection.prepareStatement(SQL_DELETE_HOTEL);
            deleteHotelStatement.setLong(1, id);
            hotelDeleted = deleteHotelStatement.executeUpdate() == 1;

            connection.commit();
        } catch (SQLException e) {
            rollbackTransaction(connection);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(deleteHotelStatement);
            this.closeStatement(updateUserRoleStatement);
            this.releaseConnection(connection);
        }
        return userRoleUpdated && hotelDeleted;
    }

    private Optional<Hotel> getHotel(Long id, String SQLQuery) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        Optional<Hotel> optionalHotel;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQLQuery);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Hotel hotel = null;
            while (resultSet.next()) {
                if (hotel == null) {
                    hotel = this.buildHotel(resultSet);
                }
                this.addApartment(resultSet, hotel);
                this.addBooking(resultSet, hotel);
            }
            optionalHotel = Optional.ofNullable(hotel);
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(statement);
            this.releaseConnection(connection);
        }
        return optionalHotel;
    }

    private void addApartment(ResultSet resultSet, Hotel hotel) throws SQLException {
        long apartmentId = resultSet.getLong(APARTMENT_ID);
        if (!hotel.getApartments().containsKey(apartmentId)) {
            hotel.getApartments().put(apartmentId, Apartment.builder()
                    .id(apartmentId)
                    .name(resultSet.getString(APARTMENT_NAME))
                    .price(resultSet.getDouble(PRICE))
                    .category(Category.values()[(resultSet.getInt(CATEGORY))])
                    .singleBedCount(resultSet.getInt(SINGLE_BED_COUNT))
                    .doubleBedCount(resultSet.getInt(DOUBLE_BED_COUNT))
                    .comfort(Comfort.builder()
                            .id(resultSet.getLong(COMFORT_ID))
                            .tv(resultSet.getBoolean(TV))
                            .conditioner(resultSet.getBoolean(CONDITIONER))
                            .bar(resultSet.getBoolean(BAR))
                            .refrigerator(resultSet.getBoolean(REFRIGERATOR))
                            .balcony(resultSet.getBoolean(BALCONY))
                            .jacuzzi(resultSet.getBoolean(JACUZZI))
                            .breakfast(resultSet.getBoolean(BREAKFAST))
                            .wifi(resultSet.getBoolean(WIFI))
                            .transfer(resultSet.getBoolean(TRANSFER))
                            .parking(resultSet.getBoolean(PARKING))
                            .swimmingPool(resultSet.getBoolean(SWIMMING_POOL))
                            .gym(resultSet.getBoolean(GYM))
                            .restaurant(resultSet.getBoolean(RESTAURANT))
                            .pets(resultSet.getBoolean(PETS))
                            .accessibilityFeatures(resultSet.getBoolean(ACCESSIBILITY_FEATURES))
                            .build())
                    .build());
        }
    }

    private void addBooking(ResultSet resultSet, Hotel hotel) throws SQLException {
        long apartmentId = resultSet.getLong(APARTMENT_ID);
        long bookingId = resultSet.getLong(BOOKING_ID);
        if (hotel.getApartments().get(apartmentId).getBookings() == null) {
            hotel.getApartments().get(apartmentId).setBookings(new LinkedHashMap<>());
        }
        if (bookingId != 0) {
            hotel.getApartments().get(apartmentId).getBookings().put(bookingId, Booking.builder()
                    .id(bookingId)
                    .dateIn(resultSet.getDate(DATE_IN).toLocalDate())
                    .dateOut(resultSet.getDate(DATE_OUT).toLocalDate())
                    .isConfirm(resultSet.getBoolean(IS_CONFIRM))
                    .build());
        }
    }

    private Hotel buildHotel(ResultSet resultSet) throws SQLException {
        return Hotel.builder()
                .id(resultSet.getLong(HOTEL_ID))
                .legalName(resultSet.getString(LEGAL_NAME))
                .brand(resultSet.getString(BRAND))
                .address(Address.builder()
                        .id(resultSet.getLong(ADDRESS_ID))
                        .country(resultSet.getString(COUNTRY))
                        .city(resultSet.getString(CITY))
                        .street(resultSet.getString(STREET))
                        .house(resultSet.getString(HOUSE))
                        .building(resultSet.getString(BUILDING))
                        .build())
                .apartments(new LinkedHashMap<>())
                .isRegistered(resultSet.getBoolean(IS_REGISTERED))
                .stars(Stars.values()[(resultSet.getInt(STARS))])
                .build();
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

    private void rollbackTransaction(ProxyConnection connection) throws RepositoryException {
        try {
            connection.rollback();
        } catch (SQLException e1) {
            throw new RepositoryException(ExceptionMessage.TRANSACTION_EXCEPTION_MESSAGE, e1);
        }
    }

    private void releaseConnection(ProxyConnection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
