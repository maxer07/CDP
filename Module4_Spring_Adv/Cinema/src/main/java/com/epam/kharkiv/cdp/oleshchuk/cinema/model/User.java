package com.epam.kharkiv.cdp.oleshchuk.cinema.model;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.Aggregate;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.Event;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.UserCreatedEvent;
import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.ReplayException;
import org.apache.commons.lang3.Validate;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends Aggregate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private BigInteger id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Ticket> tickets = new HashSet<Ticket>();

    public User() {
        super();
    }

    public User(String name) {
        createUser(Long.valueOf(new Random().nextLong()),new BigInteger(String.valueOf(new Random().nextInt())), name);
    }
    public User(Long Long, String name) {
        createUser(Long,new BigInteger(String.valueOf(new Random().nextInt())), name);
    }
    public User(BigInteger id, String name) {
        createUser(Long.valueOf(new Random().nextLong()),id, name);
    }

    protected final void createUser(Long identity, BigInteger id, String name){
        Validate.notNull(identity, "Identity cannot be null");
        Validate.notNull(name, "Name cannot be null");
        this.identity = identity;
        this.name = name;
        this.id = id;
        applyEvent( new UserCreatedEvent(this.identity, this.name));
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    protected void replay(final UserCreatedEvent event){
        createUser(event.getIdentity(), null, event.getName());
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket){
        ticket.setUser(this);
        this.getTickets().add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        this.getTickets().remove(ticket);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
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
                ", name='" + name + '\'' + "\r\n" +
                "}\\r\\n";
    }

}
