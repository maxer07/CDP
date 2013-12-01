package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.store;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.Event;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventStore {
    public void storeEvents(Long aggregateId, List<Event> events, int expectedVersion);
    public List<Event> sourceEvents(Long aggregateId);
    public int getCurrentVersion(Long aggregateId);
}
