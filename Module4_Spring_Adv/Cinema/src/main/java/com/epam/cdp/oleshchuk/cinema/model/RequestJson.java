package com.epam.cdp.oleshchuk.cinema.model;

import java.io.Serializable;
import java.util.List;

public class RequestJson implements Serializable {

    private List<Long> ticketIds;

    public List<Long> getTicketIds() {
        return ticketIds;
    }

    public void setTicketIds(List<Long> ticketIds) {
        this.ticketIds = ticketIds;
    }

    @Override
    public String toString() {
        return "RequestJson{" +
                "ticketIds=" + ticketIds +
                '}';
    }
}
