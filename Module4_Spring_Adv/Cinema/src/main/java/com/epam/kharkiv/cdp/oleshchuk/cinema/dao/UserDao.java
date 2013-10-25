package com.epam.kharkiv.cdp.oleshchuk.cinema.dao;

import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    public User findById(Long id) throws DaoException;

}
