package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.jdbc.impl;

import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.TestUserDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.jdbc.mapper.UserRowMapper;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.stereotype.Repository;


@Repository
public class TestUserJdbcMySqlDao extends AbstractJdbcMySqlDao<User, Long> implements TestUserDao {

    private static final String SQL__USER_TABLE = "user";
    private static final String SQL__INSERT_USER = "INSERT INTO " + SQL__USER_TABLE + " (name) VALUES (?)";
    private static final String SQL__DELETE_ALL_FROM_TABLE = "DELETE FROM " + SQL__USER_TABLE;
    private static final String SQL__SELECT_COUNT_ALL_FROM_TABLE = "SELECT COUNT(*) FROM " + SQL__USER_TABLE;

    public TestUserJdbcMySqlDao() {
        rowMapper = new UserRowMapper();
    }

    @Override
    public void insertUser(String userName) {
        jdbcTemplateObject.update(SQL__INSERT_USER, userName);
    }

    @Override
    public void cleanAllTable() {
       jdbcTemplateObject.update(SQL__DELETE_ALL_FROM_TABLE);
    }

    @Override
    public long getAllCount() {
        return jdbcTemplateObject.queryForInt(SQL__SELECT_COUNT_ALL_FROM_TABLE);
    }

    protected String getTable() {
        return SQL__USER_TABLE;
    }


}
