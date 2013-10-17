package com.epam.kharkiv.cdp.oleshchuk.cinema.service;


import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.ServiceException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;

public interface UserService {

    public User getUserById(Long id) throws ServiceException;

}
