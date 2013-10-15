package com.epam.cdp.oleshchuk.cinema.jdbc.RowMapper;

import com.epam.cdp.oleshchuk.cinema.model.User;
import com.epam.cdp.oleshchuk.cinema.util.Constant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(Constant.SQL__USER_TABLE__RAW_ID));
        user.setName(rs.getString(Constant.SQL__USER_TABLE__RAW_NAME));
        return user;
    }
}
