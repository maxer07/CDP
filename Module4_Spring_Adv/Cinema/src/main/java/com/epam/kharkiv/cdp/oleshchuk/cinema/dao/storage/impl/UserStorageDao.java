package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.storage.impl;

import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.UserDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class UserStorageDao implements UserDao {

    private static Map<Long, User> userMap;
    private static long currId;

    public UserStorageDao() {
        userMap = new HashMap<Long, User>();
        currId = 0;
        createUsers();
    }

    public User getUserById(Long id) throws DaoException {
        if (id == null) {
            throw new DaoException("id is null");
        } else if (!userMap.containsKey(id)) {
            throw new DaoException("There is no user with id = " + id);
        } else {
            return userMap.get(id);
        }
    }

    private void createUsers() {
        User user;
        user = new User(getNextId(), "max");
        userMap.put(user.getId(), user);
        user = new User(getNextId(), "dima");
        userMap.put(user.getId(), user);
    }

    private synchronized long getNextId() {
        return currId++;
    }
}
