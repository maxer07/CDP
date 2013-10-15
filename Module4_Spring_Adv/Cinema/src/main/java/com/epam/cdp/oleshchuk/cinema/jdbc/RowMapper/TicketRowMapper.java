package com.epam.cdp.oleshchuk.cinema.jdbc.RowMapper;

import com.epam.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.cdp.oleshchuk.cinema.model.TicketCategory;
import com.epam.cdp.oleshchuk.cinema.util.Constant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketRowMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getLong(Constant.SQL__TICKET_TABLE__RAW_ID));
        ticket.setTitle(rs.getString(Constant.SQL__TICKET_TABLE__RAW_TITLE));
        ticket.setDate(rs.getTimestamp(Constant.SQL__TICKET_TABLE__RAW_DATE));
        ticket.setTicketCategory(TicketCategory.valueOf((rs.getString(Constant.SQL__TICKET_TABLE__RAW_CATEGORY)).toUpperCase()));
        ticket.setPlace(rs.getInt(Constant.SQL__TICKET_TABLE__RAW_PLACE));
        return ticket;
    }
}
