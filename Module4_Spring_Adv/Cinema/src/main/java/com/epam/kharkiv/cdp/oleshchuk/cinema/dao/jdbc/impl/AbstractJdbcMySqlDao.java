package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.jdbc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public abstract class AbstractJdbcMySqlDao {

    @Autowired(required = false)
    protected JdbcTemplate jdbcTemplateObject;
    protected RowMapper rowMapper;


    protected abstract String getTable();

    protected Object findById(Long id) {
        String SQL = getAllFromTrable();
        return jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, rowMapper);
    }

    private String getAllFromTrable() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ").append(getTable()).append(" WHERE id = ?;");
        return stringBuilder.toString();
    }

}
