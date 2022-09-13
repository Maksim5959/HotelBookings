package com.chuyashkou.hotels_booking.service.impl;

import com.chuyashkou.hotels_booking.exception.RepositoryException;
import com.chuyashkou.hotels_booking.exception.ServiceException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.model.Role;
import com.chuyashkou.hotels_booking.model.User;
import com.chuyashkou.hotels_booking.repository.impl.UserRepositoryImpl;
import com.chuyashkou.hotels_booking.service.UserService;

import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserServiceImpl INSTANCE = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException {
        Optional<User> user;
        try {
            user = UserRepositoryImpl.getInstance().findUserByEmailAndPassword(email, password);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return user;
    }

    @Override
    public Map<Long, User> findAll() throws ServiceException {
        Map<Long, User> users;
        try {
            users = UserRepositoryImpl.getInstance().findAll();
            users.values().removeIf(user -> user.getRole() == Role.ADMIN);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return users;
    }

    @Override
    public boolean updateUserPassword(User user) throws ServiceException {
        try {
            return UserRepositoryImpl.getInstance().updateUserPassword(user);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }

    }

    @Override
    public Optional<User> deleteUserAddress(User user) throws ServiceException {
        Optional<User> userWithoutAddress;
        try {
            userWithoutAddress = UserRepositoryImpl.getInstance().deleteUserAddress(user);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return userWithoutAddress;
    }

    @Override
    public Optional<User> createUserAddress(User user) throws ServiceException {
        Optional<User> userWithAddress;
        try {
            userWithAddress = UserRepositoryImpl.getInstance().createUserAddress(user);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return userWithAddress;
    }

    @Override
    public Optional<User> updateUserAddress(User user) throws ServiceException {
        Optional<User> updateUser;
        try {
            updateUser = UserRepositoryImpl.getInstance().updateUserAddress(user);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return updateUser;
    }

    @Override
    public Optional<User> findByEmailExcludingId(String email, Long id) throws ServiceException {
        Optional<User> user;
        try {
            user = UserRepositoryImpl.getInstance().findByEmailExcludingId(email, id);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        Optional<User> user;
        try {
            user = UserRepositoryImpl.getInstance().findByEmail(email);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return user;
    }

    @Override
    public Long create(User user) throws ServiceException {
        Long id;
        try {
            id = UserRepositoryImpl.getInstance().create(user);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return id;
    }

    @Override
    public Optional<User> update(User user) throws ServiceException {
        Optional<User> updateUser;
        try {
            updateUser = UserRepositoryImpl.getInstance().update(user);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return updateUser;
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return UserRepositoryImpl.getInstance().delete(id);
        } catch (RepositoryException e) {
            throw new ServiceException(ExceptionMessage.SERVICE_EXCEPTION_MESSAGE, e);
        }
    }
}
