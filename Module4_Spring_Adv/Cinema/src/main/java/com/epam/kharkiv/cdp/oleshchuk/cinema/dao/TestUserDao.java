package com.epam.kharkiv.cdp.oleshchuk.cinema.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface TestUserDao {

    public void insertUser(String userName);

    public void cleanAllTable();

    public long getAllCount();

}
