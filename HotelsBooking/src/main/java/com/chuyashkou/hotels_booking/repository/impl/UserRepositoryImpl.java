package com.chuyashkou.hotels_booking.repository.impl;

import com.chuyashkou.hotels_booking.connection.ConnectionPool;
import com.chuyashkou.hotels_booking.connection.ProxyConnection;
import com.chuyashkou.hotels_booking.exception.ConnectionPoolException;
import com.chuyashkou.hotels_booking.exception.RepositoryException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.message.LoggerMessage;
import com.chuyashkou.hotels_booking.model.Address;
import com.chuyashkou.hotels_booking.model.Gender;
import com.chuyashkou.hotels_booking.model.Role;
import com.chuyashkou.hotels_booking.model.User;
import com.chuyashkou.hotels_booking.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private static final UserRepositoryImpl INSTANCE = new UserRepositoryImpl();

    private static final String SQL_CREATE_USER = "INSERT " +
            "INTO users (email,password, first_name, last_name, gender, phone_number, date_of_birth, role) " +
            "VALUES (?,?,?,?,?,?,?,?)";
    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users " +
            "LEFT JOIN addresses a on users.id = a.user_id WHERE users.email = ?";
    private static final String SQL_FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM users " +
            "LEFT JOIN addresses a on users.id = a.user_id WHERE users.email = ? AND users.password = ?";
    private static final String SQL_UPDATE_USER_DATA = "UPDATE users " +
            "SET email = ?, first_name = ?, last_name = ?, gender = ?, phone_number = ?, date_of_birth = ? " +
            "WHERE id = ?";
    private static final String SQL_FIND_BY_EMAIL_EXCLUDING_ID = "SELECT * FROM users " +
            "LEFT JOIN addresses a on users.id = a.user_id WHERE email = ? AND users.id != ?";
    private static final String SQL_UPDATE_USER_ADDRESS = "UPDATE addresses " +
            "SET country = ?, city = ?, street = ?, house = ?, building = ?, apartment_number = ? " +
            "WHERE id = ?";
    private static final String SQL_CREATE_USER_ADDRESS = "INSERT " +
            "INTO addresses (country, city, street, house, building, apartment_number, user_id) " +
            "VALUES(?,?,?,?,?,?,?)";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id=?";
    private static final String SQL_DELETE_USER_ADDRESS = "DELETE FROM addresses WHERE id=?";
    private static final String SQL_UPDATE_USER_PASSWORD = "UPDATE users SET password=? WHERE id=?";
    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users LEFT JOIN addresses a on users.id = a.user_id";
    private static final String USER_ID = "id";
    private static final String EMAIL = "email";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String GENDER = "gender";
    private static final String DATE_OF_BIRTH = "date_of_birth";
    private static final String ROLE = "role";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String HOUSE = "house";
    private static final String BUILDING = "building";
    private static final String APARTMENT_NUMBER = "apartment_number";
    private static final String ADDRESS_ID = "a.id";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserRepositoryImpl() {
    }

    public static UserRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        Optional<User> user = Optional.empty();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL_AND_PASSWORD);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = Optional.ofNullable(this.buildUser(resultSet));
            }
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(statement);
            this.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public Optional<User> findByEmailExcludingId(String email, Long id) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        Optional<User> user = Optional.empty();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_EMAIL_EXCLUDING_ID);
            statement.setString(1, email);
            statement.setLong(2, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = Optional.ofNullable(this.buildUser(resultSet));
            }
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(statement);
            this.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public Optional<User> updateUserAddress(User user) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        Optional<User> userWithAddress = Optional.empty();
        try {
            connection = ConnectionPool.getInstance().getConnection();

            statement = connection.prepareStatement(SQL_UPDATE_USER_ADDRESS);
            statement.setString(1, user.getAddress().getCountry());
            statement.setString(2, user.getAddress().getCity());
            statement.setString(3, user.getAddress().getStreet());
            statement.setString(4, user.getAddress().getHouse());
            statement.setString(5, user.getAddress().getBuilding());
            statement.setString(6, user.getAddress().getApartmentNumber());
            statement.setLong(7, user.getAddress().getId());
            if (statement.executeUpdate() == 1) {
                userWithAddress = Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(statement);
            this.releaseConnection(connection);
        }
        return userWithAddress;
    }

    @Override
    public Optional<User> createUserAddress(User user) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        Optional<User> userWithAddress = Optional.empty();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_CREATE_USER_ADDRESS, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getAddress().getCountry());
            statement.setString(2, user.getAddress().getCity());
            statement.setString(3, user.getAddress().getStreet());
            statement.setString(4, user.getAddress().getHouse());
            statement.setString(5, user.getAddress().getBuilding());
            statement.setString(6, user.getAddress().getApartmentNumber());
            statement.setLong(7, user.getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.getAddress().setId(resultSet.getLong(1));
                userWithAddress = Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(statement);
            this.releaseConnection(connection);
        }
        return userWithAddress;
    }

    @Override
    public Optional<User> deleteUserAddress(User user) throws RepositoryException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        Optional<User> optionalUser = Optional.empty();
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(SQL_DELETE_USER_ADDRESS);
            statement.setLong(1, user.getAddress().getId());
            if (statement.executeUpdate() == 1) {
                user.setAddress(new Address());
                optionalUser = Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.releaseConnection(proxyConnection);
            this.closeStatement(statement);
        }
        return optionalUser;
    }

    @Override
    public boolean updateUserPassword(User user) throws RepositoryException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(SQL_UPDATE_USER_PASSWORD);
            statement.setString(1, user.getPassword());
            statement.setLong(2, user.getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.releaseConnection(proxyConnection);
            this.closeStatement(statement);
        }
    }

    @Override
    public Map<Long, User> findAll() throws RepositoryException {
        ProxyConnection connection = null;
        Statement statement = null;
        Map<Long, User> users = new LinkedHashMap<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_USERS);
            while (resultSet.next()) {
                User user = this.buildUser(resultSet);
                users.put(user.getId(), user);
            }
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(statement);
            this.releaseConnection(connection);
        }
        return users;
    }

    @Override
    public Optional<User> findByEmail(String email) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        Optional<User> user = Optional.empty();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = Optional.ofNullable(this.buildUser(resultSet));
            }
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(statement);
            this.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public Long create(User user) throws RepositoryException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        Long id = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            this.setUserFields(user, statement);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) id = resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.closeStatement(statement);
            this.releaseConnection(connection);
        }
        return id;
    }

    @Override
    public Optional<User> update(User user) throws RepositoryException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        Optional<User> updateUser;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(SQL_UPDATE_USER_DATA);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getGender().name());
            statement.setString(5, user.getPhoneNumber());
            statement.setDate(6, Date.valueOf(user.getDateOfBirth()));
            statement.setLong(7, user.getId());
            statement.executeUpdate();
            updateUser = Optional.of(user);
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.releaseConnection(proxyConnection);
            this.closeStatement(statement);
        }
        return updateUser;
    }

    @Override
    public boolean delete(Long id) throws RepositoryException {
        ProxyConnection proxyConnection = null;
        PreparedStatement statement = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            statement = proxyConnection.prepareStatement(SQL_DELETE_USER_BY_ID);
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RepositoryException(ExceptionMessage.DAO_EXCEPTION_MESSAGE, e);
        } catch (ConnectionPoolException e) {
            throw new RepositoryException(ExceptionMessage.CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } finally {
            this.releaseConnection(proxyConnection);
            this.closeStatement(statement);
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong(USER_ID))
                .email(resultSet.getString(EMAIL))
                .firstName(resultSet.getString(FIRST_NAME))
                .lastName(resultSet.getString(LAST_NAME))
                .gender(Gender.valueOf(resultSet.getString(GENDER).toUpperCase()))
                .phoneNumber(resultSet.getString(PHONE_NUMBER))
                .dateOfBirth(resultSet.getDate(DATE_OF_BIRTH).toLocalDate())
                .role(Role.values()[(resultSet.getInt(ROLE))])
                .address(Address.builder()
                        .id(resultSet.getLong(ADDRESS_ID))
                        .country(resultSet.getString(COUNTRY))
                        .city(resultSet.getString(CITY))
                        .street(resultSet.getString(STREET))
                        .house(resultSet.getString(HOUSE))
                        .building(resultSet.getString(BUILDING))
                        .apartmentNumber(resultSet.getString(APARTMENT_NUMBER))
                        .build())
                .build();
    }

    private void setUserFields(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getFirstName());
        statement.setString(4, user.getLastName());
        statement.setString(5, user.getGender().name());
        statement.setString(6, user.getPhoneNumber());
        statement.setDate(7, Date.valueOf(user.getDateOfBirth()));
        statement.setInt(8, user.getRole().ordinal());
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
