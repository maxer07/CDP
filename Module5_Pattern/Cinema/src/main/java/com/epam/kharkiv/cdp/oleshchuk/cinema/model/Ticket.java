package com.epam.kharkiv.cdp.oleshchuk.cinema.model;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.Aggregate;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.Event;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.ticket.TicketBookedEvent;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.ticket.TicketCreatedEvent;
import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.ReplayException;
import org.apache.commons.lang3.Validate;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket extends Aggregate implements Comparable<Ticket>, Serializable {

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

    public Ticket(Long identity, String title, Date date, TicketCategory category, Integer place, User user,
                  String studio, List<String> starringActors, String description) {
        createTicket(identity, new BigInteger(String.valueOf(generateObjectId())), title, date, category, place, studio, starringActors, description, user);
    }

    public Ticket(Long identity, BigInteger id, String title, Date date, TicketCategory category, Integer place, User user,
                  String studio, List<String> starringActors, String description) {
        createTicket(identity, id, title, date, category, place, studio, starringActors, description, user);
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

    @Override
    protected void replay(final Event event){
        try{
            Method m = getClass().getDeclaredMethod("replay", event.getClass());
            m.invoke(this,event);
        }catch(Exception e){
            throw new ReplayException("Failed to replay contact with id =>" + this.identity, e);
        }
    }

    public void bookTicket(final User user){
        Validate.notNull(user, "User cannot be null! Cannot book the ticket.");
        this.user = user;
        applyEvent( new TicketBookedEvent(this.identity, this.user));
    }



    protected void replay(final TicketBookedEvent event){
        bookTicket(event.getUser());
    }

    protected void replay(final TicketCreatedEvent event){
        createTicket(event.getIdentity(), null, event.getTitle(), event.getDate(), event.getCategory(), event.getPlace(),
                event.getStudio(), event.getStarringActors(), event.getDescription(), event.getUser());
    }

    protected final void createTicket(Long identity, BigInteger id, String title, Date date, TicketCategory category, Integer place,
                                      String studio, List<String> starringActors, String description, User user){
        Validate.notNull(identity, "Identity cannot be null");
        this.identity = identity;
        this.id = id;
        this.title = title;
        this.date = date;
        this.category = category;
        this.place = place;
        this.studio = studio;
        this.starringActors = starringActors;
        this.description = description;
        this.user = user;
        applyEvent( new TicketCreatedEvent(identity, title, date, category, place, studio,
                starringActors, description, user));
    }
}
