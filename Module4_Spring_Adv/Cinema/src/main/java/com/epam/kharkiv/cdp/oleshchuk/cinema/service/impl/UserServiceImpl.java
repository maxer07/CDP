package com.epam.kharkiv.cdp.oleshchuk.cinema.service.impl;

import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.UserDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.ServiceException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import com.epam.kharkiv.cdp.oleshchuk.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier(value = "aliasUserDao")
    private UserDao userDao;

    public User getUserById(Long id) throws ServiceException {
        try {
            return userDao.getUserById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
