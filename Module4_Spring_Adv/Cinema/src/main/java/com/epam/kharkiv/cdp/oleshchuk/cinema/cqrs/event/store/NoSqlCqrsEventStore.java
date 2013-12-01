package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.store;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.Event;
import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.CqrsVersionException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.EventModel;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.StoredEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class NoSqlCqrsEventStore implements EventStore{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void storeEvents(Long aggregateId, List<Event> events, int expectedVersion) {
        EventModel eventModel = mongoTemplate.findOne(new BasicQuery(" {identity : " + aggregateId + "}"), EventModel.class);
        if (eventModel == null) eventModel = new EventModel();
        checkForStaleData(aggregateId, expectedVersion);

            for(Event event : events){
                expectedVersion++;
                eventModel.getStoredEvent().add(new StoredEvent(event, expectedVersion));
            }
        eventModel.setIdentity(aggregateId);
        mongoTemplate.save(eventModel);
    }


    protected void checkForStaleData(Long aggregateId, int expectedVersion){
        int lastVersion = getCurrentVersion(aggregateId);
        if(expectedVersion!=lastVersion){
            throw new CqrsVersionException("Expected version was " + expectedVersion +
                    " but last committed version was " + lastVersion);
        }
    }

    @Override
    public int getCurrentVersion(Long aggregateId){
        EventModel eventModel = mongoTemplate.findOne(new BasicQuery(" {identity : " + aggregateId + "}"), EventModel.class);
        if (eventModel == null) eventModel = new EventModel();
        if(eventModel.getStoredEvent()==null || eventModel.getStoredEvent().size() ==0){
            return 0;
        }else{
            StoredEvent last = eventModel.getStoredEvent().get(eventModel.getStoredEvent().size() - 1);
            return last.getVersion();
        }
    }

    @Override
    public List<Event> sourceEvents(Long aggregateId) {
        EventModel eventModel = mongoTemplate.findOne(new BasicQuery(" {identity : " + aggregateId + "}"), EventModel.class);
        if (eventModel == null) eventModel = new EventModel();
        final List<Event> events = new LinkedList<Event>();

        if(eventModel.getStoredEvent()!=null && eventModel.getStoredEvent().size()>0){

            for(StoredEvent e : eventModel.getStoredEvent()){
                events.add(e.getEvent());
            }
        }
        return events;
    }

}
