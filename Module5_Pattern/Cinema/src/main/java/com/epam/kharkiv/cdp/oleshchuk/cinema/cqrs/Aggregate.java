package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.Event;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.LinkedList;
import java.util.List;

public abstract class Aggregate {

    @JsonIgnore
    protected Long identity;
    @JsonIgnore
    protected int version;
    @JsonIgnore
    private List<Event> uncommittedChanges = new LinkedList<Event>();


    public Long getIdentity() {
        return identity;
    }

    public int getVersion() {
        return version;
    }

    public List<Event> getUncommittedChanges(){
        return this.uncommittedChanges;
    }

    public void markChangesCommitted(){
        this.uncommittedChanges.clear();
    }


    protected void applyEvent(Event event){
        this.uncommittedChanges.add(event);
    }

    public void loadFromHistory(List<Event> eventsFromHistory, int currentVersion){
        for(Event event : eventsFromHistory ){
            replay(event);
        }
        this.markChangesCommitted();
        this.version = currentVersion;
    }

    protected abstract void replay(Event event);

    private static Long aggregateId = 0L;
    private static int objectId = 0;

    protected synchronized Long generateAggregateId() {
         return aggregateId++;
    }
    protected synchronized int generateObjectId() {
        return objectId++;
    }


}
