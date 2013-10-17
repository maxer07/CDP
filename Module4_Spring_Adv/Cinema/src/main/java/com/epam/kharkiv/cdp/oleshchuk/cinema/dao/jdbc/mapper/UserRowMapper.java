package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.jdbc.mapper;

import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    private static final String SQL__USER_TABLE__RAW_ID = "id";
    private static final String SQL__USER_TABLE__RAW_NAME = "name";

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(SQL__USER_TABLE__RAW_ID));
        user.setName(rs.getString(SQL__USER_TABLE__RAW_NAME));
        return user;
    }
}
