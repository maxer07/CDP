package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.storage.impl;

import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.TicketDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.TicketCategory;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.TicketsFilterParams;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.storage.filter.TicketFilter;
import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.storage.filter.TicketFilterByCategory;
import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.storage.filter.TicketFilterByDate;
import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.storage.filter.TicketFilterByTitle;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TicketStorageDao implements TicketDao {

    private static Map<Ticket, User> ticketMap;
    private static long currId;

    public TicketStorageDao() {
        ticketMap = new HashMap<Ticket, User>();
        createTickets();
    }

    public List<Ticket> getAvailableTickets(TicketsFilterParams ticketsFilterParams) {
        List<Ticket> freeTickets = new ArrayList<Ticket>();
        List<Ticket> filteredTickets = new ArrayList<Ticket>();
        for (Map.Entry<Ticket, User> entry : ticketMap.entrySet()) {
            if (entry.getValue() == null) {
                freeTickets.add(entry.getKey());
            }
        }
        TicketFilter ticketFilter = getTicketFilterChain(ticketsFilterParams);
        filteredTickets = ticketFilter.doFilter(freeTickets);
        Collections.sort(filteredTickets);
        return filteredTickets;
    }

    public List<Ticket> getTicketsByUser(User user, TicketsFilterParams ticketsFilterParams) throws DaoException {
        if (user == null) {
            throw new DaoException("Could not get ticket by user = null");
        }
        List<Ticket> userTickets = new ArrayList<Ticket>();
        List<Ticket> filteredTickets = new ArrayList<Ticket>();
        for (Map.Entry<Ticket, User> entry : ticketMap.entrySet()) {
            if (user.equals(entry.getValue())) {
                userTickets.add(entry.getKey());
            }
        }
        TicketFilter ticketFilter = getTicketFilterChain(ticketsFilterParams);
        filteredTickets = ticketFilter.doFilter(userTickets);
        Collections.sort(filteredTickets);
        return filteredTickets;
    }


    public Ticket getTicketById(Long id) throws DaoException {
        Ticket ticket = null;
        if (id == null) {
            throw new DaoException("Get ticket Id is null");
        }
        for (Map.Entry<Ticket, User> entry : ticketMap.entrySet()) {
            if (entry.getKey().getId() == id) {
                ticket = entry.getKey();
                break;
            }
        }
        if (ticket == null) {
            throw new DaoException("There is no ticket with id=" + id);
        }
        return ticket;
    }

    public synchronized void bookTicket(List<Long> ticketIds, User user) throws DaoException {
        if (ticketIds == null) {
            throw new DaoException("Ticket ids is null");
        }
        List<Ticket> bookedTickets = new ArrayList<Ticket>();
        Ticket ticket;
        for (Long id : ticketIds) {
            ticket = getTicketById(id);
            if (ticketMap.get(ticket) != null) {
                bookedTickets.add(ticket);
            }
        }
        if (bookedTickets.size() == 0) {
            for (Long ticketId : ticketIds) {
                ticket = getTicketById(ticketId);
                if (ticketMap.get(ticket) != null) {
                    throw new DaoException(ticket + " is already booked");
                }
                if (ticket != null && user != null) {
                    ticketMap.put(ticket, user);
                } else {
                    throw new DaoException("Ticket or user is null: ticket = " + ticket + ", user = " + user);
                }
            }
        } else {
            throw new DaoException("Tickets already booked : " + bookedTickets);
        }
    }

    private void createTickets() {
        long now = System.currentTimeMillis();
        generateAndPutTickets(10, "Shaolin football", new Date(now), TicketCategory.STANDART);
        generateAndPutTickets(10, "Shaolin football", new Date(now), TicketCategory.BAR);
        generateAndPutTickets(10, "Shaolin football", new Date(now), TicketCategory.PREMIUM);
        generateAndPutTickets(5, "Mission Impossible", new Date(now + 86400000), TicketCategory.STANDART);
        generateAndPutTickets(5, "Mission Impossible", new Date(now + 86400000), TicketCategory.PREMIUM);
        generateAndPutTickets(5, "An interesting film", new Date(now - 172800000), TicketCategory.BAR);
        generateAndPutTickets(5, "An interesting film", new Date(now - 172800000), TicketCategory.STANDART);

    }

    private void generateAndPutTickets(int count, String title, Date date, TicketCategory category) {
        Ticket ticket;
        for (int i = 1; i < count + 1; i++) {
            ticket = new Ticket(getNextId(), title, date, category, i);
            ticketMap.put(ticket, null);
        }
    }

    private synchronized long getNextId() {
        return currId++;
    }

    private TicketFilter getTicketFilterChain(TicketsFilterParams ticketsFilterParams) {
        TicketFilter filterByTitle = new TicketFilterByTitle(ticketsFilterParams.getTitle());
        TicketFilter filterByCategory = new TicketFilterByCategory(ticketsFilterParams.getCategory());
        TicketFilter filterByDate = new TicketFilterByDate(ticketsFilterParams.getDateFrom(), ticketsFilterParams.getDateTo());
        filterByCategory.setNextChain(filterByDate);
        filterByTitle.setNextChain(filterByCategory);
        return filterByTitle;
    }
}
