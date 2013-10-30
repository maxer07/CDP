package com.epam.kharkiv.cdp.oleshchuk.cinema.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket implements Comparable<Ticket>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "title")
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private TicketCategory category;

    @Column(name = "place")
    private Integer place;

    private String studio;

    private List<String> starringActors;

    private String description;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    public Ticket() {
    }

    public Ticket(BigInteger id, String title, Date date, TicketCategory category,
                  Integer place, User user, String studio, List<String> starringActors,
                  String description) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.category = category;
        this.place = place;
        this.studio = studio;
        this.starringActors = starringActors;
        this.description = description;
        this.user = user;
    }

    public Ticket(String title, Date date, TicketCategory category, Integer place, User user,
                  String studio, List<String> starringActors, String description) {
        this.title = title;
        this.date = date;
        this.category = category;
        this.place = place;
        this.studio = studio;
        this.starringActors = starringActors;
        this.description = description;
        this.user = user;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public List<String> getStarringActors() {
        return starringActors;
    }

    public void setStarringActors(List<String> starringActors) {
        this.starringActors = starringActors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(BigInteger id) {
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

    public void setCategory(TicketCategory category) {
        this.category = category;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", category=" + category +
                ", place=" + place +
                ", user=" + user +
                '}';
    }
}
