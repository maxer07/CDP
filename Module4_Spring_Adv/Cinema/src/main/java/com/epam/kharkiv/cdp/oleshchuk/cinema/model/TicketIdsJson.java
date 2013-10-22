package com.epam.kharkiv.cdp.oleshchuk.cinema.model;

import java.io.Serializable;
import java.util.List;

public class TicketIdsJson implements Serializable {

    private List<Long> ticketIds;

    public List<Long> getTicketIds() {
        return ticketIds;
    }

    @Override
    public String toString() {
        return "RequestJson{" +
                "ticketIds=" + ticketIds +
                '}';
    }
}
