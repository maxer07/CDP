package com.epam.cdp.oleshchuk.cinema.model;

import java.io.Serializable;
import java.util.List;

public class RequestJson implements Serializable {

    //private static final long serialVersionUID = 1513207428686438208L;

    private List<Long> ticketId;

    public RequestJson() {
    }

    public RequestJson(List<Long> ticketId) {
        this.ticketId = ticketId;
    }

    public List<Long> getTicketId() {
        return ticketId;
    }

    public void setTicketId(List<Long> ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {
        return "RequestJson{" +
                "ticketId=" + ticketId +
                '}';
    }
}
