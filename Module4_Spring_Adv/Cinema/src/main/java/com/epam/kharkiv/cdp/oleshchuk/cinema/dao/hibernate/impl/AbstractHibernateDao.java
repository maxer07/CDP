package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.hibernate.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public abstract class AbstractHibernateDao <E, K extends Serializable> {

    @Autowired
    protected SessionFactory sessionFactory;

    protected void saveOrUpdate(E obj){
        sessionFactory.getCurrentSession().saveOrUpdate(obj);

    }

    public E findById(K id) {
        return (E) sessionFactory.getCurrentSession().get(getEntityClass(), id);
    }

    protected abstract Class<E> getEntityClass();

}
