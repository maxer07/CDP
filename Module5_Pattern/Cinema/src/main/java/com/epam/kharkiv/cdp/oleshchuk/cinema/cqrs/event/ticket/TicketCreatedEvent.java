package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.ticket;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.Event;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.TicketCategory;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;

import java.util.Date;
import java.util.List;

public class TicketCreatedEvent implements Event {

    private Long identity;
    private String title;
    private Date date;
    private TicketCategory category;
    private Integer place;
    private String studio;
    private List<String> starringActors;
    private String description;
    private User user;

    public TicketCreatedEvent(Long identity, String title, Date date, TicketCategory category, Integer place,
                              String studio, List<String> starringActors, String description, User user) {
        this.identity = identity;
        this.title = title;
        this.date = date;
        this.category = category;
        this.place = place;
        this.studio = studio;
        this.starringActors = starringActors;
        this.description = description;
        this.user = user;
    }

    public Long getIdentity() {
        return identity;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public TicketCategory getCategory() {
        return category;
    }

    public Integer getPlace() {
        return place;
    }

    public String getStudio() {
        return studio;
    }

    public List<String> getStarringActors() {
        return starringActors;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }
}
