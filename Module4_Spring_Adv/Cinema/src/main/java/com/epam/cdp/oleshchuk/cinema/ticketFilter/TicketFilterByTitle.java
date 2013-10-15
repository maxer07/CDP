package com.epam.cdp.oleshchuk.cinema.ticketFilter;

import com.epam.cdp.oleshchuk.cinema.model.Ticket;

public class TicketFilterByTitle extends TicketFilter {

    private String title;

    public TicketFilterByTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean isFiltered(Ticket ticket) {
        if (getResultFromNextChain(ticket)) {
            if (title == null) {
                passFilter = true;
                return passFilter;
            }
            passFilter = (ticket.getTitle().toLowerCase().contains(title.toLowerCase()));
        }
        return passFilter;
    }
}