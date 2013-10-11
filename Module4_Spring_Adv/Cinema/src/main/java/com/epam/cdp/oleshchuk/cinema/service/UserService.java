package com.epam.cdp.oleshchuk.cinema.service;


import com.epam.cdp.oleshchuk.cinema.exception.ServiceException;
import com.epam.cdp.oleshchuk.cinema.model.User;

import java.util.List;

public interface UserService {
    public User getUserById(Long id) throws ServiceException;
    public List<User> getAllUsers();
}
