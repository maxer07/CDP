package com.epam.cdp.oleshchuk.cinema.service;

import com.epam.cdp.oleshchuk.cinema.exception.ServiceException;
import com.epam.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.cdp.oleshchuk.cinema.model.TicketsFilterParams;
import com.epam.cdp.oleshchuk.cinema.model.User;

import java.util.List;

public interface TicketService {

    public List<Ticket> getAvailableTickets(TicketsFilterParams ticketsFilterParams);

    public void bookTicket(List<Long> ticketIds, User user) throws ServiceException;

    public Ticket getTicketById(Long id) throws ServiceException;

    public List<Ticket> getTicketsByUser(User user, TicketsFilterParams ticketsFilterParams) throws ServiceException;

}
