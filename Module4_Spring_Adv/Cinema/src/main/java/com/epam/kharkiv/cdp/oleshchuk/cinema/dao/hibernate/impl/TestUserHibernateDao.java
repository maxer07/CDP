package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.hibernate.impl;

import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.TestUserDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.TestUser;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class TestUserHibernateDao extends AbstractHibernateDao implements TestUserDao{

    @Override
    public void insertUser(String userName) {
        TestUser tempUser = new TestUser(1L, userName);
        saveOrUpdate(tempUser);
    }

    @Override
    public void cleanAllTable() {
       sessionFactory.getCurrentSession().createQuery("delete from TestUser").executeUpdate();
    }

    @Override
    public long getAllCount() {
        return (Long) sessionFactory.getCurrentSession().createCriteria(TestUser.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    protected Class getEntityClass() {
        return TestUser.class;
    }
}
