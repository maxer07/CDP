package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.hibernate.impl;

import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.TestUserDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Transactional
@Repository
public class TestUserHibernateDao extends AbstractHibernateDao<User, Long> implements TestUserDao {

    @Override
    public void insertUser(String userName) {
        User tempUser = new User(new BigInteger("1"), userName);
        saveOrUpdate(tempUser);
    }

    @Override
    public void cleanAllTable() {
       sessionFactory.getCurrentSession().createQuery("delete from User").executeUpdate();
    }

    @Override
    public long getAllCount() {
        return (Long) sessionFactory.getCurrentSession().createCriteria(User.class).
                setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    protected Class getEntityClass() {
        return User.class;
    }
}
