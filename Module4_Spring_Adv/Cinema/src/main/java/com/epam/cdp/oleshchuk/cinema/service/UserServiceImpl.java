package com.epam.cdp.oleshchuk.cinema.service;

import com.epam.cdp.oleshchuk.cinema.dao.UserDao;
import com.epam.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.cdp.oleshchuk.cinema.exception.ServiceException;
import com.epam.cdp.oleshchuk.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(Long id) throws ServiceException {
        User user = null;
        try {
            user = userDao.getUserById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }




}
