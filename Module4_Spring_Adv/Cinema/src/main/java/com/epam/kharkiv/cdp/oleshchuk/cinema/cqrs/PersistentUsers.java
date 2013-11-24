
package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.Event;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.EventStore;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersistentUsers implements UserRepository{

    @Autowired
    private EventStore eventStore;

    public PersistentUsers(final EventStore eventStore){
        this.eventStore = eventStore;
    }

    @Override
    public void store(final User user) {
        final List<Event> events = user.getUncommittedChanges();
        eventStore.storeEvents(user.getIdentity(), events, user.getVersion());
        user.markChangesCommitted();
    }

    @Override
    public User load(Long aggregateId) {
        int version = eventStore.getCurrentVersion(aggregateId);
        final User user = new User();
        user.loadFromHistory(eventStore.sourceEvents(aggregateId), version);
        return user;
    }

    protected PersistentUsers(){}
}
