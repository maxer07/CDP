package com.epam.cdp.oleshchuk.cinema.model;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Ticket implements Comparable<Ticket> {

    private Long id;
    private String title;
    private Date date;
    private TicketCategory ticketCategory;
    private Integer place;

    public Ticket() {
        super();
    }

    public Ticket(String title, Date date, TicketCategory ticketCategory, Integer place) {
        this.title = title;
        this.date = date;
        this.ticketCategory = ticketCategory;
        this.place = place;
    }

    public Ticket(Long id, Ticket ticket) {
        this(ticket.title, ticket.date, ticket.ticketCategory, ticket.place);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public TicketCategory getTicketCategory() {
        return ticketCategory;
    }

    public Integer getPlace() {
        return place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        if (!id.equals(ticket.id)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(Ticket o) {
        int result = (int) (this.getDate().getTime() - o.getDate().getTime());
        if (result == 0) {
            result = (this.getTitle().compareTo(o.getTitle()));
            if (result == 0) {
                result = (this.getTicketCategory().getPriority() - o.getTicketCategory().getPriority());
                if (result == 0) {
                    result = this.getPlace() - o.getPlace();
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "title='" + title + '\'' +
                ", date=" + new SimpleDateFormat("dd/MM/yy").format(date) +
                ", ticketCategory=" + ticketCategory +
                ", place=" + place +
                '}';
    }


}
