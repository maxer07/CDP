package com.epam.cdp.oleshchuk.cinema.dao.jdbcImpl;

import com.epam.cdp.oleshchuk.cinema.dao.TicketDao;
import com.epam.cdp.oleshchuk.cinema.exception.DaoException;
import com.epam.cdp.oleshchuk.cinema.jdbc.RowMapper.TicketRowMapper;
import com.epam.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.cdp.oleshchuk.cinema.model.TicketsFilterParams;
import com.epam.cdp.oleshchuk.cinema.model.User;
import com.epam.cdp.oleshchuk.cinema.util.Constant;
import com.epam.cdp.oleshchuk.cinema.util.QueryBuilderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("ticketJdbcMySqlDao")
public class TicketJdbcMySqlDao implements TicketDao {

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public List<Ticket> getAvailableTickets(TicketsFilterParams ticketsFilterParams) {
        String SQL = QueryBuilderUtil.createQueryWithFilterParams(Constant.SQL__QUERY__TICKET_GET_ALL, ticketsFilterParams);
        List<Ticket> ticketList = jdbcTemplateObject.query(SQL, new TicketRowMapper());
        return ticketList;
    }

    @Override
    public Ticket getTicketById(Long id) throws DaoException {
        String SQL = Constant.SQL__QUERY__TICKET_GET_BY_TICKET_ID;
        Ticket ticket = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new TicketRowMapper());
        return ticket;
    }

    @Override
    public synchronized void bookTicket(List<Long> ticketIds, User user) throws DaoException {
        String SQL = QueryBuilderUtil.createQueryWithInCause(Constant.SQL__QUERY__TICKET_GET_BOOKED_BY_TICKET_IDS, ticketIds.size());
        List<Ticket> bookedTicketsList = jdbcTemplateObject.query(SQL, ticketIds.toArray(), new TicketRowMapper());
        if (bookedTicketsList.size() == 0) {
            SQL = Constant.SQL__QUERY__TICKET_BOOK_BY_USER;
            for (Long ticketId : ticketIds) {
                jdbcTemplateObject.update(SQL, user.getId(), ticketId);
            }
        } else {
            throw new DaoException("Tickets already booked : " + bookedTicketsList);
        }
    }

    @Override
    public List<Ticket> getTicketsByUser(User user, TicketsFilterParams ticketsFilterParams) throws DaoException {
        String SQL = QueryBuilderUtil.createQueryWithFilterParams(Constant.SQL__QUERY__TICKET_GET_BY_USER, ticketsFilterParams);
        List<Ticket> ticketList = jdbcTemplateObject.query(SQL, new Object[]{user.getId()}, new TicketRowMapper());
        return ticketList;
    }
}
