package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.jdbc.impl;

import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.UserDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.jdbc.mapper.UserRowMapper;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


@Repository
public class UserJdbcMySqlDao extends AbstractJdbcMySqlDao<User, BigInteger> implements UserDao {

    private static final String SQL__USER_TABLE = "user";

    public UserJdbcMySqlDao() {
        rowMapper = new UserRowMapper();
    }

    protected String getTable() {
        return SQL__USER_TABLE;
    }

}
