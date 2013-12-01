package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.Event;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.store.EventStore;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketCqrsRepositoryImpl extends AbstractCqrsRepository<Ticket, Long> {

    public TicketCqrsRepositoryImpl(){
        super(Ticket.class);
    }

}
