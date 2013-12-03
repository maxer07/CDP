package com.epam.kharkiv.cdp.oleshchuk.cinema.service.impl;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.ticket.TicketBookedCommand;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.ticket.TicketCommandHandlers;
import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.TicketDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.ServiceException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.TicketsFilterParams;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import com.epam.kharkiv.cdp.oleshchuk.cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TicketServiceImpl implements TicketService {

    @Autowired
    @Qualifier(value = "aliasTicketDao")
    TicketDao ticketDao;
    @Autowired
    TicketCommandHandlers ticketCommandHandlers;

    public List<Ticket> getAvailableTickets(TicketsFilterParams ticketsFilterParams) throws ServiceException {
        try {
            return ticketDao.getAvailableTickets(ticketsFilterParams);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void bookTicket(List<BigInteger> ticketIds, User user) throws ServiceException {
        try {
            List<Ticket> bookedTickets = ticketDao.bookTicket(ticketIds, user);
            for (Ticket bokkedTicket : bookedTickets) {
                ticketCommandHandlers.handle(new TicketBookedCommand(bokkedTicket.getIdentity(), user));
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    public List<Ticket> getTicketsByUser(User user, TicketsFilterParams ticketsFilterParams) throws ServiceException {
        try {
            return ticketDao.getTicketsByUser(user, ticketsFilterParams);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}