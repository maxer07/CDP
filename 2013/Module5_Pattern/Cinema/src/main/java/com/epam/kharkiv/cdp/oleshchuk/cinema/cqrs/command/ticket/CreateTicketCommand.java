package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.ticket;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.Command;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.TicketCategory;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class CreateTicketCommand implements Command {

    private String title;
    private Date date;
    private TicketCategory category;
    private Integer place;
    private String studio;
    private List<String> starringActors;
    private String description;
    private User user;
    private long identity;
    private BigInteger id;

    public CreateTicketCommand(String title, Date date, TicketCategory category, Integer place,
                               String studio, List<String> starringActors, String description, User user,
                               long identity, BigInteger id) {
        this.title = title;
        this.date = date;
        this.category = category;
        this.place = place;
        this.studio = studio;
        this.starringActors = starringActors;
        this.description = description;
        this.user = user;
        this.id = id;
        this.identity = identity;

    }

    public BigInteger getId() {
        return id;
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

    public long getIdentity() {
        return identity;
    }
}
