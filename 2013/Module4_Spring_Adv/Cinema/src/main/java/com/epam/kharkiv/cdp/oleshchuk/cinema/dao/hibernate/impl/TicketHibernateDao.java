package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.hibernate.impl;

import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.TicketDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.TicketsFilterParams;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketHibernateDao extends AbstractHibernateDao<Ticket, BigInteger> implements TicketDao {

    @Override
    public List<Ticket> getAvailableTickets(TicketsFilterParams ticketsFilterParams) throws DaoException {
        String HQL = createHqlWithFilterParams("from Ticket t where t.user is null", ticketsFilterParams);
        try {
            return sessionFactory.getCurrentSession().createQuery(HQL).list();
        } catch (Exception e) {
            throw new DaoException("Sorry, you have entered bad params", e);
        }
    }

    @Override
    public synchronized void bookTicket(List<BigInteger> ticketIds, User user) throws DaoException {
        List<Ticket> bookedTicketsList = new ArrayList<Ticket>();
        for (BigInteger ticketId : ticketIds) {
            Ticket ticket = (Ticket) sessionFactory.getCurrentSession().
                    createQuery("from Ticket t where t.user is not null and t.id = :ticketId").
                    setParameter("ticketId", ticketId).uniqueResult();
            if (ticket != null) {
                bookedTicketsList.add(ticket);
            }
        }
        if (bookedTicketsList.size() == 0) {
            for (BigInteger ticketId : ticketIds) {
                Ticket ticket = findById(ticketId);
                ticket.setUser(user);
                saveOrUpdate(ticket);
            }
        } else {
            throw new DaoException("Tickets already booked : " + bookedTicketsList);
        }
    }

    @Override
    public List<Ticket> getTicketsByUser(User user, TicketsFilterParams ticketsFilterParams) throws DaoException {
        if (user == null) {
            throw new DaoException("User with such id is unavailable");
        }
        String HQL = createHqlWithFilterParams("from Ticket t where t.user.id = :userId", ticketsFilterParams);
        try {
            return sessionFactory.getCurrentSession().createQuery(HQL).setParameter("userId", user.getId()).list();
        } catch (Exception e) {
            throw new DaoException("Sorry, you have entered bad params", e);
        }
    }

    private String createHqlWithFilterParams(String hql, TicketsFilterParams ticketsFilterParams) {
        StringBuilder stringBuilder = new StringBuilder(hql);
        if (ticketsFilterParams.getTitle() != null) {
            createAndConditionForString(stringBuilder, ticketsFilterParams.getTitle(), "title");
        }
        if (ticketsFilterParams.getCategory() != null) {
            createAndConditionForString(stringBuilder, ticketsFilterParams.getCategory(), "category");
        }
        if (ticketsFilterParams.getDateFrom() != null) {
            createAndConditionForDateFrom(stringBuilder, ticketsFilterParams.getDateFrom(), "date");
        }
        if (ticketsFilterParams.getDateTo() != null) {
            createAndConditionForDateTo(stringBuilder, ticketsFilterParams.getDateTo(), "date");
        }
        return stringBuilder.toString();
    }

    private void createAndConditionForString(StringBuilder stringBuilder, String filterParam, String rawName) {
        stringBuilder.append(" AND (lower(t.").append(rawName).append(") like lower('%").
                append(filterParam).append("%'))");
    }

    private void createAndConditionForDateFrom(StringBuilder stringBuilder, String filterParam, String rawName) {
        stringBuilder.append(" AND (t.").append(rawName).append(" >= '").
                append(filterParam).append("')");
    }

    private void createAndConditionForDateTo(StringBuilder stringBuilder, String filterParam, String rawName) {
        stringBuilder.append(" AND (t.").append(rawName).append(" <= '").
                append(filterParam).append("')");
    }

    @Override
    protected Class<Ticket> getEntityClass() {
        return Ticket.class;
    }
}