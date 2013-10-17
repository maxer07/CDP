package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.jdbc.impl;

import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.TicketDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.jdbc.mapper.TicketRowMapper;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.TicketsFilterParams;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.jdbc.util.QueryBuilderUtil;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TicketJdbcMySqlDao extends AbstractJdbcMySqlDao implements TicketDao {

    private static final String SQL__TICKET_TABLE = "ticket";
    private static final String SQL__QUERY__TICKET_GET_ALL = "SELECT * FROM " + SQL__TICKET_TABLE + " WHERE user_id is NULL";
    private static final String SQL__QUERY__TICKET_GET_BY_USER = "SELECT * FROM " + SQL__TICKET_TABLE + " WHERE user_id = ?";
    private static final String SQL__QUERY__TICKET_BOOK_BY_USER = "UPDATE " + SQL__TICKET_TABLE + " SET user_id = ? WHERE id = ?";
    private static final String SQL__QUERY__TICKET_GET_BOOKED_BY_TICKET_IDS = "SELECT * FROM ticket WHERE (user_id is NOT NULL) AND (id IN (?))";


    public TicketJdbcMySqlDao() {
        rowMapper = new TicketRowMapper();
    }

    @Override
    public List<Ticket> getAvailableTickets(TicketsFilterParams ticketsFilterParams) {
        String SQL = createQueryWithFilterParams(SQL__QUERY__TICKET_GET_ALL, ticketsFilterParams);
        List<Ticket> ticketList = jdbcTemplateObject.query(SQL, rowMapper);
        return ticketList;
    }

    @Override
    public Ticket getTicketById(Long id) throws DaoException {
        return (Ticket) super.findById(id);
    }

    @Override
    public synchronized void bookTicket(List<Long> ticketIds, User user) throws DaoException {
        String SQL = QueryBuilderUtil.createQueryWithInCause(SQL__QUERY__TICKET_GET_BOOKED_BY_TICKET_IDS, ticketIds.size());
        List<Ticket> bookedTicketsList = jdbcTemplateObject.query(SQL, ticketIds.toArray(), rowMapper);
        if (bookedTicketsList.size() == 0) {
            SQL = SQL__QUERY__TICKET_BOOK_BY_USER;
            for (Long ticketId : ticketIds) {
                jdbcTemplateObject.update(SQL, user.getId(), ticketId);
            }
        } else {
            throw new DaoException("Tickets already booked : " + bookedTicketsList);
        }
    }

    @Override
    public List<Ticket> getTicketsByUser(User user, TicketsFilterParams ticketsFilterParams) throws DaoException {
        String SQL = createQueryWithFilterParams(SQL__QUERY__TICKET_GET_BY_USER, ticketsFilterParams);
        List<Ticket> ticketList = jdbcTemplateObject.query(SQL, new Object[]{user.getId()}, rowMapper);
        return ticketList;
    }

    protected String getTable() {
        return SQL__TICKET_TABLE;
    }


    private String createQueryWithFilterParams(String sql, TicketsFilterParams ticketsFilterParams) {
        StringBuilder stringBuilder = new StringBuilder(sql);
        if (ticketsFilterParams.getTitle() != null) {
            createAndConditionForString(stringBuilder, ticketsFilterParams.getTitle(), "title");
        }
        if (ticketsFilterParams.getCategory() != null) {
            createAndConditionForString(stringBuilder, ticketsFilterParams.getCategory(), "category");
        }
        if (ticketsFilterParams.getDateFrom() != null) {
            createAndConditionForDateFrom(stringBuilder, ticketsFilterParams.getDateFrom(), "date");
        }
        if (ticketsFilterParams.getDateTo() != null) {
            createAndConditionForDateTo(stringBuilder, ticketsFilterParams.getDateTo(), "date");
        }
        return stringBuilder.toString();

    }

    private static void createAndConditionForString(StringBuilder stringBuilder, String filterParam, String rawName) {
        stringBuilder.append(" AND (").append(rawName).append(" LIKE '%").append(filterParam).append("%')");
    }

    private static void createAndConditionForDateFrom(StringBuilder stringBuilder, String filterParam, String rawName) {
        stringBuilder.append(" AND (").append(rawName).append(" >= '").append(filterParam).append("')");
    }

    private static void createAndConditionForDateTo(StringBuilder stringBuilder, String filterParam, String rawName) {
        stringBuilder.append(" AND (").append(rawName).append(" <= '").append(filterParam).append("')");
    }
}
