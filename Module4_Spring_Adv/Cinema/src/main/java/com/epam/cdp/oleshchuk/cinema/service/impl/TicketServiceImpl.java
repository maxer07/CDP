package com.epam.cdp.oleshchuk.cinema.service.impl;

import com.epam.cdp.oleshchuk.cinema.dao.TicketDao;
import com.epam.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.cdp.oleshchuk.cinema.exception.ServiceException;
import com.epam.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.cdp.oleshchuk.cinema.model.TicketsFilterParams;
import com.epam.cdp.oleshchuk.cinema.model.User;
import com.epam.cdp.oleshchuk.cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class TicketServiceImpl implements TicketService {

    @Autowired
    @Qualifier(value = "ticketJdbcMySqlDao")
    TicketDao ticketDao;

    public List<Ticket> getAvailableTickets(TicketsFilterParams ticketsFilterParams) {
        return ticketDao.getAvailableTickets(ticketsFilterParams);
    }

    public Ticket getTicketById(Long id) throws ServiceException {
        Ticket ticket;
        try {
            ticket = ticketDao.getTicketById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return ticket;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void bookTicket(List<Long> ticketIds, User user) throws ServiceException {
        try {
            ticketDao.bookTicket(ticketIds, user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    public List<Ticket> getTicketsByUser(User user, TicketsFilterParams ticketsFilterParams) throws ServiceException {
        List<Ticket> userTickets = null;
        try {
            userTickets = ticketDao.getTicketsByUser(user, ticketsFilterParams);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return userTickets;
    }

}