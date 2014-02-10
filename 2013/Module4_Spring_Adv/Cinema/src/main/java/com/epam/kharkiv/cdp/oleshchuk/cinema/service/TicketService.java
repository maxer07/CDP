package com.epam.kharkiv.cdp.oleshchuk.cinema.service;

import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.ServiceException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.TicketsFilterParams;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;

import java.math.BigInteger;
import java.util.List;

public interface TicketService {

    public List<Ticket> getAvailableTickets(TicketsFilterParams ticketsFilterParams) throws ServiceException;

    public void bookTicket(List<BigInteger> ticketIds, User user) throws ServiceException;

    public List<Ticket> getTicketsByUser(User user, TicketsFilterParams ticketsFilterParams) throws ServiceException;

}
