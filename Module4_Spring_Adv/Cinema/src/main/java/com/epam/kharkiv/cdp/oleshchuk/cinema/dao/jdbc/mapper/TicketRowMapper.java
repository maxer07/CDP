package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.jdbc.mapper;

import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketRowMapper implements RowMapper<Ticket> {
    private static final String SQL__TICKET_TABLE__RAW_ID = "id";
    private static final String SQL__TICKET_TABLE__RAW_TITLE = "title";
    private static final String SQL__TICKET_TABLE__RAW_DATE = "date";
    private static final String SQL__TICKET_TABLE__RAW_CATEGORY = "category";
    private static final String SQL__TICKET_TABLE__RAW_PLACE = "place";

    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getLong(SQL__TICKET_TABLE__RAW_ID));
        ticket.setTitle(rs.getString(SQL__TICKET_TABLE__RAW_TITLE));
        ticket.setDate(rs.getTimestamp(SQL__TICKET_TABLE__RAW_DATE));
        ticket.setCategory((rs.getString(SQL__TICKET_TABLE__RAW_CATEGORY)));
        ticket.setPlace(rs.getInt(SQL__TICKET_TABLE__RAW_PLACE));
        return ticket;
    }
}
