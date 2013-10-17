package com.epam.kharkiv.cdp.oleshchuk.cinema.model;

import java.util.Date;

public class Ticket implements Comparable<Ticket> {

    private Long id;
    private String title;
    private Date date;
    private TicketCategory category;
    private Integer place;

    public Ticket() {
        super();
    }

    public Ticket(Long id, String title, Date date, TicketCategory category, Integer place) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.category = category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = TicketCategory.valueOf(category);
    }

    public void setPlace(Integer place) {
        this.place = place;
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

    public TicketCategory getCategory() {
        return category;
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
                result = (this.getCategory().getPriority() - o.getCategory().getPriority());
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
                "id=" + id + "\r\n" +
                ", title='" + title + '\'' + "\r\n" +
                ", date=" + date +
                ", ticketCategory=" + category + "\r\n" +
                ", place=" + place + "\r\n" +
                "}\r\n";
    }
}
