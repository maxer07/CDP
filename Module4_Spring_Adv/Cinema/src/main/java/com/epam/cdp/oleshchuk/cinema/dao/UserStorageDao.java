package com.epam.cdp.oleshchuk.cinema.dao;

import com.epam.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.cdp.oleshchuk.cinema.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;


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
        }
        else if (!userMap.containsKey(id)) {
            throw new DaoException("There is no user with id = " + id);
        }
        else {
            return userMap.get(id);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        User user = null;
        for (Map.Entry<Long, User> entry : userMap.entrySet()){
            user = entry.getValue();
            if (user!=null) {
                userList.add(user);
            }
        }
        return userList;

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
