package com.epam.cdp.oleshchuk.cinema.dao;

import com.epam.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.cdp.oleshchuk.cinema.model.User;

import java.util.List;

public interface TicketDao {
    public List<Ticket> getAvailableTicket();
    public Ticket getTicketById(Long id) throws DaoException;
    public void bookTicketById(Ticket ticket, User user) throws DaoException;
    public List<Ticket> getTicketsByUser(User user) throws DaoException;
    public List<Ticket> getBookedTickets(List<Long> ticketIds) throws DaoException;
}
