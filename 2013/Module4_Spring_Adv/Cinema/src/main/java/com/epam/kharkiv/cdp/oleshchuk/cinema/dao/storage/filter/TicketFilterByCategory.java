package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.storage.filter;

import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.TicketCategory;

public class TicketFilterByCategory extends TicketFilter {

    private String category;

    public TicketFilterByCategory(String categoty) {
        this.category = categoty;
    }

    @Override
    public boolean isFiltered(Ticket ticket) {
        if (getResultFromNextChain(ticket)) {
            TicketCategory ticketCategory;
            try {
                ticketCategory = TicketCategory.valueOf(category.toUpperCase());
            } catch (Exception e) {
                passFilter = true;
                return passFilter;
            }
            return (ticketCategory == ticket.getCategory());
        }
        return passFilter;
    }
}
