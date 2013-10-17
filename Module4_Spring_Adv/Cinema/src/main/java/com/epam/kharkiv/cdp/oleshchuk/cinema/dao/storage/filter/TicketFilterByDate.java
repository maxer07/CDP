package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.storage.filter;

import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketFilterByDate extends TicketFilter {

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    private String dateFrom;
    private String dateTo;

    public TicketFilterByDate(String dateFrom, String dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @Override
    public boolean isFiltered(Ticket ticket) {
        if (getResultFromNextChain(ticket)) {
            if (dateFrom == null || dateTo == null) {
                passFilter = true;
                return passFilter;
            }
            Date dateFromObj = null;
            Date dateToObj = null;
            SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
            try {
                dateFromObj = format.parse(dateFrom);
                dateToObj = format.parse(dateTo);
            } catch (ParseException e) {
                passFilter = true;
                return passFilter;
            }
            passFilter = (ticket.getDate().after(dateFromObj) && ticket.getDate().before(dateToObj));
        }
        return passFilter;
    }
}