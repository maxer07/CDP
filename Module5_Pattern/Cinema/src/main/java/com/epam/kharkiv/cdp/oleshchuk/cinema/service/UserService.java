package com.epam.kharkiv.cdp.oleshchuk.cinema.service;


import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.ServiceException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;

import java.math.BigInteger;

public interface UserService {

    public User getUserById(BigInteger id) throws ServiceException;

}
