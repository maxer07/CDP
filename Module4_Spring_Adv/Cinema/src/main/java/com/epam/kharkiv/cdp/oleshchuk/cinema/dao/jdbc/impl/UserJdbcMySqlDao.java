package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.jdbc.impl;

import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.UserDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.jdbc.mapper.UserRowMapper;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.stereotype.Repository;


@Repository
public class UserJdbcMySqlDao extends AbstractJdbcMySqlDao<User, Long> implements UserDao {

    private static final String SQL__USER_TABLE = "user";

    public UserJdbcMySqlDao() {
        rowMapper = new UserRowMapper();
    }

    protected String getTable() {
        return SQL__USER_TABLE;
    }

}
