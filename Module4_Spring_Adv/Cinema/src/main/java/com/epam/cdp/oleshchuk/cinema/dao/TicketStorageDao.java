package com.epam.cdp.oleshchuk.cinema.dao;

import com.epam.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.cdp.oleshchuk.cinema.model.TicketCategory;
import com.epam.cdp.oleshchuk.cinema.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TicketStorageDao implements TicketDao {

    private static Map<Ticket, User> ticketMap;
    private static long currId;

    public TicketStorageDao() {
        ticketMap = new HashMap<Ticket, User>();
        currId = 0;
        createTickets();
    }

    public List<Ticket> getAvailableTickets() {
        List<Ticket> freeTickets = new ArrayList<Ticket>();
        for (Map.Entry<Ticket, User> entry : ticketMap.entrySet()) {
            if (entry.getValue() == null) {
                freeTickets.add(entry.getKey());
            }
        }
        Collections.sort(freeTickets);
        return freeTickets;
    }

    public List<Ticket> getTicketsByUser(User user) throws DaoException {
        if (user == null) {
            throw new DaoException("Could not get ticket by user = null");
        }
        List<Ticket> userTickets = new ArrayList<Ticket>();
        for (Map.Entry<Ticket, User> entry : ticketMap.entrySet()) {
            if (user.equals(entry.getValue())) {
                userTickets.add(entry.getKey());
            }
        }
        Collections.sort(userTickets);
        return userTickets;
    }


    public Ticket getTicketById(Long id) throws DaoException {
        Ticket ticket = null;
        if (id == null) {
           throw new DaoException("Get ticket Id is null");
       }
        for (Map.Entry<Ticket, User> entry : ticketMap.entrySet()) {
            if (entry.getKey().getId()==id) {
                ticket = entry.getKey();
                break;
            }
        }
        if (ticket==null) {
            throw new DaoException("There is no ticket with id=" + id);
        }
        return ticket;
    }

    public synchronized void bookTicket(Ticket ticket, User user) throws DaoException {
        if (ticketMap.get(ticket)!=null) {
            throw new DaoException(ticket + " is already booked");
        }
        if (ticket!=null && user != null) {
            ticketMap.put(ticket, user);
        }
        else {
            throw new DaoException("Ticket or user is null: ticket = " + ticket + ", user = " + user);
        }
    }

    public List<Ticket> getBookedTicketsByTicketIds(List<Long> ticketIds) throws DaoException {
        if (ticketIds == null) {
            throw new DaoException("Ticket ids is null");
        }
        List<Ticket> bookedTickets = new ArrayList<Ticket>();
        Ticket ticket;
        for (Long id : ticketIds) {
            ticket = getTicketById(id);
            if (ticketMap.get(ticket)!=null) {
                bookedTickets.add(ticket);
            }
        }
        return bookedTickets;
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
}
