package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.storage.filter;

import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public abstract class TicketFilter {

    protected TicketFilter nextChain;
    protected boolean passFilter = true;

    abstract boolean isFiltered(Ticket ticket);

    public List<Ticket> doFilter(List<Ticket> ticketList) {
        List<Ticket> filteredTickets = new ArrayList<Ticket>();
        for (Ticket ticket : ticketList) {
            if (isFiltered(ticket)) {
                filteredTickets.add(ticket);
            }
        }
        return filteredTickets;
    }

    public void setNextChain(TicketFilter nextChain) {
        this.nextChain = nextChain;
    }

    protected boolean getResultFromNextChain(Ticket ticket) {
        passFilter = true;
        if (nextChain != null) {
            passFilter = nextChain.isFiltered(ticket);
        }
        return passFilter;
    }

}
