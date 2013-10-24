package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.hibernate.impl;

import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.UserDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserHibernateDao extends AbstractHibernateDao<User, Long> implements UserDao  {

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

}
