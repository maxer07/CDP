package com.epam.kharkiv.cdp.oleshchuk.cinema.dao;

import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserDao {

    public User findById(BigInteger id) throws DaoException;

}
