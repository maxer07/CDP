package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.Event;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.EventStore;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersistentTickets implements TicketRepository{

    @Autowired
    private EventStore eventStore;

    public PersistentTickets(){
        super();
    }

    @Override
    public void store(final Ticket ticket) {
        final List<Event> events = ticket.getUncommittedChanges();
        eventStore.storeEvents(ticket.getIdentity(), events, ticket.getVersion());
        ticket.markChangesCommitted();
    }

    @Override
    public Ticket load(Long aggregateId) {
        int version = eventStore.getCurrentVersion(aggregateId);
        final Ticket ticket = new Ticket();
        ticket.loadFromHistory(eventStore.sourceEvents(aggregateId), version);
        return ticket;
    }


}
