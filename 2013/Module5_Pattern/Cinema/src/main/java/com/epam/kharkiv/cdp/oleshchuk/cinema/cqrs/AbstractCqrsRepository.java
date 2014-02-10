package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.Event;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.store.EventStore;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AbstractCqrsRepository<T extends Aggregate,R> {

    @Autowired
    protected EventStore eventStore;

    final Class<T> typeParameterClass;

    public AbstractCqrsRepository(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public void store(final T aggregateObject) {
        final List<Event> events = aggregateObject.getUncommittedChanges();
        eventStore.storeEvents(aggregateObject.getIdentity(), events, aggregateObject.getVersion());
        aggregateObject.markChangesCommitted();
    }

    public T load(R aggregateId)  {
        int version = eventStore.getCurrentVersion((Long) aggregateId);
        T t = null;
        try {
            t = typeParameterClass.newInstance();
        } catch (Exception e) {
           throw new RuntimeException("Bad aggregate class");
        }
        t.loadFromHistory(eventStore.sourceEvents((Long) aggregateId), version);
        return t;
    }



}
