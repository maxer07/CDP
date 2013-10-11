package com.epam.cdp.oleshchuk.cinema.dao;

import com.epam.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.cdp.oleshchuk.cinema.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    public User getUserById(Long id) throws DaoException;
    public List<User> getAllUsers();
}
