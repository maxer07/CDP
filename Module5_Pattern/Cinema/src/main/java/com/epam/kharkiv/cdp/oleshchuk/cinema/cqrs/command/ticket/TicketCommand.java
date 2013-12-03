package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.ticket;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.Command;
import org.apache.commons.lang3.Validate;


public class TicketCommand implements Command {

    private Long ticketId;

    public TicketCommand(Long ticketId){
        Validate.notNull(ticketId, "Ticket Id is required");
        this.ticketId = ticketId;
    }

    public Long getTicketId() {
        return ticketId;
    }
}
