package com.epam.kharkiv.cdp.oleshchuk.cinema.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "eventModel")
public class EventModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private BigInteger id;

    @Column(name = "events")
    @Field("storedEvent")
    private List<StoredEvent> storedEvent;

    @Column(name = "identity")
    private long identity;

    public EventModel() {
        super();
        this.storedEvent = new ArrayList<StoredEvent>();
    }

    public EventModel(List<StoredEvent> storedEvent, long identity) {
        this.storedEvent = storedEvent;
        this.identity = identity;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public List<StoredEvent> getStoredEvent() {
        return storedEvent;
    }

    public void setStoredEvent(List<StoredEvent> storedEvent) {
        this.storedEvent = storedEvent;
    }

    public long getIdentity() {
        return identity;
    }

    public void setIdentity(long identity) {
        this.identity = identity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventModel)) return false;
        EventModel user = (EventModel) o;
        if (id != user.id) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "User{" + "\r\n" +
                "id=" + id + "\r\n" +
                ", storedEvents='" + storedEvent + '\'' + "\r\n" +
                "}\\r\\n";
    }



}
