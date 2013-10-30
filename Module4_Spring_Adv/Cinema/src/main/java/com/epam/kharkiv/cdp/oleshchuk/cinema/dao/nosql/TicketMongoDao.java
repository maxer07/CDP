package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.nosql;

import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.TicketDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.TicketsFilterParams;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketMongoDao implements TicketDao {

    @Autowired
    MongoOperations mongoTemplate;


    @Override
    public List<Ticket> getAvailableTickets(TicketsFilterParams ticketsFilterParams) throws DaoException {
        BasicQuery query = new BasicQuery(createQueryWithFilterParams("{ user : {$exists: false}}", ticketsFilterParams));
        return mongoTemplate.find(query, Ticket.class);
    }

    @Override
    public Ticket findById(BigInteger id) throws DaoException {
        return mongoTemplate.findById(id, Ticket.class);
    }

    @Override
    public void bookTicket(List<BigInteger> ticketIds, User user) throws DaoException {
        List<Ticket> bookedTickets = new ArrayList<Ticket>();
        List<Ticket> ticketToBeBooked = new ArrayList<Ticket>();
        List<Ticket> availableTickets = getAvailableTickets(null);
        for (BigInteger bigInteger : ticketIds) {
            Ticket ticket = findById(bigInteger);
            if (!availableTickets.contains(ticket)) {
                bookedTickets.add(ticket);
            } else {
                ticket.setUser(user);
                ticketToBeBooked.add(ticket);
            }
        }
        if (bookedTickets.size() != 0) {
            throw new DaoException("Tickets" + bookedTickets + "are already booked");
        }
        for (Ticket ticket : ticketToBeBooked) {
            save(ticket);
        }

    }

    @Override
    public List<Ticket> getTicketsByUser(User user, TicketsFilterParams ticketsFilterParams) throws DaoException {
        StringBuilder query = new StringBuilder("{\"user.name\" : \"");
        query.append(user.getName()).append("\"}");
        String queryWithParams = createQueryWithFilterParams(query.toString(), ticketsFilterParams);
        return mongoTemplate.find(new BasicQuery(queryWithParams), Ticket.class);
    }

    public void save(Ticket ticket) {
        mongoTemplate.save(ticket);
    }

    private String createQueryWithFilterParams(String sql, TicketsFilterParams ticketsFilterParams) {
        StringBuilder stringBuilder = new StringBuilder("{ $and: [");
        stringBuilder.append(sql);
        if (ticketsFilterParams != null && !ticketsFilterParams.allParamsIsNull()) {
            stringBuilder.append(", { $or: [ ");
            if (ticketsFilterParams.getTitle() != null) {
                createAndConditionForString(stringBuilder, ticketsFilterParams.getTitle(), "title");
            }
            if (ticketsFilterParams.getCategory() != null) {
                createAndConditionForString(stringBuilder, ticketsFilterParams.getCategory(), "category");
            }
            if (ticketsFilterParams.getStarringActors() != null) {
                createAndConditionForList(stringBuilder, ticketsFilterParams.getStarringActors(), "starringActors");
            }
            if (ticketsFilterParams.getStudio() != null) {
                createAndConditionForString(stringBuilder, ticketsFilterParams.getStudio(), "studio");
            }
            if (ticketsFilterParams.getDateFrom() != null) {
                createAndConditionForDateFrom(stringBuilder, ticketsFilterParams.getDateFrom(), "date");
            }
            if (ticketsFilterParams.getDateTo() != null) {
                createAndConditionForDateTo(stringBuilder, ticketsFilterParams.getDateTo(), "date");
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stringBuilder.append("]}");
        }
        stringBuilder.append("]}");
        return stringBuilder.toString();

    }

    private void createAndConditionForString(StringBuilder stringBuilder, String filterParam, String rawName) {
        stringBuilder.append(" { ").append(rawName).append(" : { $regex : '").append(filterParam).
                append("', $options: 'i' } } ,");
    }

    private void createAndConditionForList(StringBuilder stringBuilder, List<String> filterParam, String rawName) {
        stringBuilder.append(" { ").append(rawName).append(" : { $in : [");
        for (String actor : filterParam) {
            stringBuilder.append("'").append(actor).append("' ,");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("]} } ,");
    }

    private void createAndConditionForDateFrom(StringBuilder stringBuilder, String filterParam, String rawName) {
        stringBuilder.append(" { ").append(rawName).append(" : {$gte :  { $date : '").append(filterParam).
                append("T00:00:00.000Z'}}} ,");
    }

    private void createAndConditionForDateTo(StringBuilder stringBuilder, String filterParam, String rawName) {
        stringBuilder.append(" { ").append(rawName).append(" : {$lte :  { $date : '").append(filterParam).
                append("T00:00:00.000Z'}}} ,");
    }

}
