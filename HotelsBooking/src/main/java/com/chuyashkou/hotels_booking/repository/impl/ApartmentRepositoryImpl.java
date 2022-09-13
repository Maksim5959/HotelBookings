package com.chuyashkou.hotels_booking.repository.impl;

import com.chuyashkou.hotels_booking.connection.ConnectionPool;
import com.chuyashkou.hotels_booking.connection.ProxyConnection;
import com.chuyashkou.hotels_booking.exception.ConnectionPoolException;
import com.chuyashkou.hotels_booking.exception.RepositoryException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.message.LoggerMessage;
import com.chuyashkou.hotels_booking.model.Apartment;
import com.chuyashkou.hotels_booking.model.Comfort;
import com.chuyashkou.hotels_booking.repository.ApartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Optional;

public class ApartmentRepositoryImpl implements ApartmentRepository {

    private static final ApartmentRepositoryImpl INSTANCE = new ApartmentRepositoryImpl();

    private static final String SQL_DELETE_APARTMENT_BY_ID = "DELETE FROM apartments WHERE id = ?";
    private static final String SQL_ADD_HOTEL_APARTMENT = "INSERT " +
            "INTO apartments (apartment_name, price, category, single_bed_count," +
            " double_bed_count, hotel_id) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE_APARTMENT_BY_ID = "UPDATE apartments " +
            "SET apartment_name = ?, price = ?, category = ?, " +
            "single_bed_count = ?, double_bed_count = ? WHERE id = ?";
    private static final String SQL_ADD_COMFORT = "INSERT " +
            "INTO comforts (tv, conditioner, bar, refrigerator, balcony, jacuzzi, " +
            "breakfast, wifi, transfer, parking, swimming_pool, gym, restaurant, pets, accessibility_features, apartment_id) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_COMFORT_BY_ID = "UPDATE comforts " +
            "SET tv = ?, conditioner = ?, bar = ?, refrigerator = ?, " +
            "balcony = ?, jacuzzi = ?, breakfast = ?, wifi = ?, " +
            "transfer = ?, parking = ?, swimming_pool = ?, gym = ?, " +
            "restaurant = ?, pets = ?, accessibility_features = ?  " +
            "WHERE id = ?";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ApartmentRepositoryImpl() {
    }

    public static ApartmentRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Long create(Apartment apartment) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement addApartmentStatement = null;
        PreparedStatement addComfortStatement = null;
        Comfort comfort = apartment.getComfort();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            addApartmentStatement = connection.prepareStatement(SQL_ADD_HOTEL_APARTMENT, PreparedStatement.RETURN_GENERATED_KEYS);
            addApartmentStatement.setString(1, apartment.getName());
            addApartmentStatement.setDouble(2, apartment.getPrice());
            addApartmentStatement.setInt(3, apartment.getCategory().ordinal());
            addApartmentStatement.setInt(4, apartment.getSingleBedCount());
            addApartmentStatement.setInt(5, apartment.getDoubleBedCount());
            addApartmentStatement.setLong(6, apartment.getHotelId());
            addApartmentStatement.executeUpdate();
            ResultSet resultSet = addApartmentStatement.getGeneratedKeys();
            if (resultSet.next()) {
                apartment.setId(resultSet.getLong(1));
            }

            addComfortStatement = connection.prepareStatement(SQL_ADD_COMFORT);
            addComfortStatement.setBoolean(1, comfort.isTv());
            addComfortStatement.setBoolean(2, comfort.isConditioner());
            addComfortStatement.setBoolean(3, comfort.isBar());
            addComfortStatement.setBoolean(4, comfort.isRefrigerator());
            addComfortStatement.setBoolean(5, comfort.isBalcony());
            addComfortStatement.setBoolean(6, comfort.isJacuzzi());
            addComfortStatement.setBoolean(7, comfort.isBreakfast());
            addComfortStatement.setBoolean(8, comfort.isWifi());
            addComfortStatement.setBoolean(9, comfort.isTransfer());
            addComfortStatement.setBoolean(10, comfort.isParking());
            addComfortStatement.setBoolean(11, comfort.isSwimmingPool());
            addComfortStatement.setBoolean(12, comfort.isGym());
            addComfortStatement.setBoolean(13, comfort.isRestaurant());
            addComfortStatement.setBoolean(14, comfort.isPets());
            addComfortStatement.setBoolean(15, comfort.isAccessibilityFeatures());
            addComfortStatement.setLong(16, apartment.getId());
            addComfortStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            rollbackTransaction(connection);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(addApartmentStatement);
            this.closeStatement(addComfortStatement);
            this.releaseConnection(connection);
        }
        return apartment.getId();
    }

    @Override
    public Optional<Apartment> update(Apartment apartment) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement updateApartmentStatement = null;
        PreparedStatement updateComfortStatement = null;
        boolean apartmentUpdated;
        boolean comfortUpdated;
        Optional<Apartment> optionalApartment = Optional.empty();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            updateApartmentStatement = connection.prepareStatement(SQL_UPDATE_APARTMENT_BY_ID);
            updateApartmentStatement.setString(1, apartment.getName());
            updateApartmentStatement.setDouble(2, apartment.getPrice());
            updateApartmentStatement.setInt(3, apartment.getCategory().ordinal());
            updateApartmentStatement.setInt(4, apartment.getSingleBedCount());
            updateApartmentStatement.setInt(5, apartment.getDoubleBedCount());
            updateApartmentStatement.setLong(6, apartment.getId());
            apartmentUpdated = updateApartmentStatement.executeUpdate() == 1;

            updateComfortStatement = connection.prepareStatement(SQL_UPDATE_COMFORT_BY_ID);
            updateComfortStatement.setBoolean(1, apartment.getComfort().isTv());
            updateComfortStatement.setBoolean(2, apartment.getComfort().isConditioner());
            updateComfortStatement.setBoolean(3, apartment.getComfort().isBar());
            updateComfortStatement.setBoolean(4, apartment.getComfort().isRefrigerator());
            updateComfortStatement.setBoolean(5, apartment.getComfort().isBalcony());
            updateComfortStatement.setBoolean(6, apartment.getComfort().isJacuzzi());
            updateComfortStatement.setBoolean(7, apartment.getComfort().isBreakfast());
            updateComfortStatement.setBoolean(8, apartment.getComfort().isWifi());
            updateComfortStatement.setBoolean(9, apartment.getComfort().isTransfer());
            updateComfortStatement.setBoolean(10, apartment.getComfort().isParking());
            updateComfortStatement.setBoolean(11, apartment.getComfort().isSwimmingPool());
            updateComfortStatement.setBoolean(12, apartment.getComfort().isGym());
            updateComfortStatement.setBoolean(13, apartment.getComfort().isRestaurant());
            updateComfortStatement.setBoolean(14, apartment.getComfort().isPets());
            updateComfortStatement.setBoolean(15, apartment.getComfort().isAccessibilityFeatures());
            updateComfortStatement.setLong(16, apartment.getComfort().getId());
            comfortUpdated = updateComfortStatement.executeUpdate() == 1;

            if (apartmentUpdated && comfortUpdated) optionalApartment = Optional.of(apartment);

            connection.commit();
        } catch (SQLException e) {
            rollbackTransaction(connection);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(updateApartmentStatement);
            this.closeStatement(updateComfortStatement);
            this.releaseConnection(connection);
        }
        return optionalApartment;
    }

    @Override
    public boolean delete(Long id) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();

            statement = connection.prepareStatement(SQL_DELETE_APARTMENT_BY_ID);
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
