package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.nosql;

import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.UserDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


@Repository
public class UserMongoDao implements UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User findById(BigInteger id) throws DaoException {
        return mongoTemplate.findById(id, User.class);
    }

}
