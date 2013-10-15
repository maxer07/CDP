package com.epam.cdp.oleshchuk.cinema.dao.jdbcImpl;

import com.epam.cdp.oleshchuk.cinema.dao.UserDao;
import com.epam.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.cdp.oleshchuk.cinema.jdbc.RowMapper.UserRowMapper;
import com.epam.cdp.oleshchuk.cinema.model.User;
import com.epam.cdp.oleshchuk.cinema.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userJdbcMySqlDao")
public class UserJdbcMySqlDao implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public User getUserById(Long id) throws DaoException {
        String SQL = Constant.SQL__QUERY__USER_GET_BY_ID;
        User user = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new UserRowMapper());
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        String SQL = Constant.SQL__QUERY__USER_GET_ALL;
        userList = jdbcTemplateObject.query(SQL, new UserRowMapper());
        return userList;
    }


}
