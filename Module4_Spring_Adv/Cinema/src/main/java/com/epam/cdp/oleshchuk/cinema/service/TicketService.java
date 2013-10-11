package com.epam.cdp.oleshchuk.cinema.service;

import com.epam.cdp.oleshchuk.cinema.exception.ServiceException;
import com.epam.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.cdp.oleshchuk.cinema.model.User;

import java.util.List;

public interface TicketService {
    public List<Ticket> getAvailableTickets();
    public void bookTicket(Ticket ticket, User user) throws ServiceException;
    public Ticket getTicketById(Long id) throws ServiceException;
    public List<Ticket> getTicketsByUser(User user) throws ServiceException;
    public List<Ticket> getBookedTicketsByTicketsIds(List<Long> ticketIds) throws ServiceException;
    public List<Ticket> filterTicketsByDate(List<Ticket> ticketList, String dateFrom, String dateTo) throws ServiceException;
    public List<Ticket> filterTicketsByCategory(List<Ticket> ticketList, String category) throws ServiceException;
    public List<Ticket> filterTicketsByTitle(List<Ticket> ticketList, String title) throws ServiceException;

}
