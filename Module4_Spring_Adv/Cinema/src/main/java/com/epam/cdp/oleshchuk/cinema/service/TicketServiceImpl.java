package com.epam.cdp.oleshchuk.cinema.service;

import com.epam.cdp.oleshchuk.cinema.dao.TicketDao;
import com.epam.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.cdp.oleshchuk.cinema.exception.ServiceException;
import com.epam.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.cdp.oleshchuk.cinema.model.TicketCategory;
import com.epam.cdp.oleshchuk.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketDao ticketDao;

    public List<Ticket> getAvailableTickets() {
        return ticketDao.getAvailableTickets();
    }

    public Ticket getTicketById(Long id) throws ServiceException {
        Ticket ticket;
        try {
            ticket = ticketDao.getTicketById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage() , e);
        }
        return ticket;
    }

    public void bookTicket(Ticket ticket, User user) throws ServiceException {
        try {
            ticketDao.bookTicket(ticket, user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Ticket> getTicketsByUser(User user) throws ServiceException {
        List<Ticket> userTickets = null;
        try {
            userTickets = ticketDao.getTicketsByUser(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return userTickets;
    }

    public List<Ticket> getBookedTicketsByTicketsIds(List<Long> ticketIds) throws ServiceException {
        try {
            return ticketDao.getBookedTicketsByTicketIds(ticketIds);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Ticket> filterTicketsByTitle(List<Ticket> ticketList, String title) throws ServiceException {
        if (ticketList == null || ticketList.size() == 0) {
            throw new ServiceException("There are no tickets for filtering");
        }
        List<Ticket> filteredTickets = new ArrayList<Ticket>();
        for (Ticket ticket : ticketList) {
            if (ticket.getTitle().contains(title)) {
                filteredTickets.add(ticket);
            }
        }
        return filteredTickets;
    }

    public List<Ticket> filterTicketsByCategory(List<Ticket> ticketList, String category) throws ServiceException {
        if (ticketList == null || ticketList.size() == 0) {
            throw new ServiceException("There are no tickets for filtering");
        }
        if (category == null) {
            throw new ServiceException("Category param is null");
        }
        TicketCategory ticketCategory;
        try {
            ticketCategory = TicketCategory.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        List<Ticket> filteredTickets = new ArrayList<Ticket>();
        for (Ticket ticket : ticketList) {
            if (ticket.getTicketCategory() == ticketCategory) {
                filteredTickets.add(ticket);
            }
        }
        return filteredTickets;
    }

    public List<Ticket> filterTicketsByDate(List<Ticket> ticketList, String dateFrom, String dateTo) throws ServiceException {
        if (ticketList == null || ticketList.size() == 0) {
            throw new ServiceException("There are no tickets for filtering");
        }
        if (dateFrom == null || dateTo == null) {
            throw new ServiceException("Date param is null");
        }
        Date dateFromObj = null;
        Date dateToObj = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");
        try {
            dateFromObj = format.parse(dateFrom);
            dateToObj = format.parse(dateTo);
        } catch (ParseException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        List<Ticket> filteredTickets = new ArrayList<Ticket>();
        for (Ticket ticket : ticketList) {
            Date ticketDate = ticket.getDate();
            if (ticketDate.after(dateFromObj) && ticketDate.before(dateToObj)) {
                filteredTickets.add(ticket);
            }
        }
        return filteredTickets;
    }


}


