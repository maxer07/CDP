package com.epam.kharkiv.cdp.oleshchuk.cinema.dao;

import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.TicketsFilterParams;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;

import java.util.List;

public interface TicketDao {

    public List<Ticket> getAvailableTickets(TicketsFilterParams ticketsFilterParams) throws DaoException;

    public Ticket findById(Long id) throws DaoException;

    public void bookTicket(List<Long> ticketIds, User user) throws DaoException;

    public List<Ticket> getTicketsByUser(User user, TicketsFilterParams ticketsFilterParams) throws DaoException;

}
