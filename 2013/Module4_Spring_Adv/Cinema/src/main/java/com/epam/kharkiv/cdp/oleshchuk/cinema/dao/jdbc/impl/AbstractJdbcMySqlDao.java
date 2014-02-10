package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.jdbc.impl;

import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;


public abstract class AbstractJdbcMySqlDao <E, K extends Serializable> {

    @Autowired(required = false)
    protected JdbcTemplate jdbcTemplateObject;
    protected RowMapper rowMapper;


    protected abstract String getTable();

    public E findById(K id) throws DaoException{
        String SQL = getAllFromTrable();
        return (E) jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, rowMapper);
    }

    private String getAllFromTrable() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ").append(getTable()).append(" WHERE id = ?;");
        return stringBuilder.toString();
    }

}
