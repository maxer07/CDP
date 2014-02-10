package com.epam.kharkiv.cdp.oleshchuk.cinema.model;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.Event;

import java.io.Serializable;

public class StoredEvent implements Serializable{

    private Event event;
    private int version;

    public StoredEvent() {
    }

    public StoredEvent(Event e, int version){
        this.event = e;
        this.version = version;
    }

    public Event getEvent(){ return this.event;}
    public int getVersion(){ return this.version;}
}
