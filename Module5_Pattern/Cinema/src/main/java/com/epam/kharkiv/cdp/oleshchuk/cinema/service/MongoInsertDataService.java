package com.epam.kharkiv.cdp.oleshchuk.cinema.service;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.ticket.CreateTicketCommand;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.ticket.TicketCommandHandlers;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.user.CreateUserCommand;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.user.UserCommandHandlers;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.TicketCategory;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MongoInsertDataService implements InitializingBean {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserCommandHandlers userCommandHandlers;
    @Autowired
    private TicketCommandHandlers ticketCommandHandlers;

    private static long id = 1000L;

    @Override
    public void afterPropertiesSet() throws Exception {
        deleteCollection("ticket");
        deleteCollection("user");
        deleteCollection("eventModel");
        generateUsers();
        generateTickets();

    }

    public void save(Object obj) {
        mongoTemplate.save(obj);
    }

    private void deleteCollection(String collectionName) {
        mongoTemplate.dropCollection(collectionName);
    }


    private void generateUsers() {
        userCommandHandlers.handle(new CreateUserCommand("max"));
        userCommandHandlers.handle(new CreateUserCommand("dima"));
        save(new User(new BigInteger("0"), "max"));
        save(new User(new BigInteger("1"), "dima"));

    }

    private void generateTickets() {
        long now = System.currentTimeMillis();
        List<String> starringActors = new ArrayList<String>();
        starringActors.add("Leonardo DiCaprio");
        starringActors.add("Rachel McAdams");
        starringActors.add("Keanu Reeves");
        generateAndPutTickets(5, "Shaolin football", new Date(now), TicketCategory.STANDART, "Paramaunt pictures",
                starringActors, "Football film");
        starringActors = new ArrayList<String>();
        starringActors.add("Johnny Depp");
        starringActors.add("Reese Witherspoon");
        starringActors.add("Michael Fassbender");
        generateAndPutTickets(5, "Bad film", new Date(now), TicketCategory.BAR, "Some pictues",
                starringActors, "Very, very bad film");
        starringActors = new ArrayList<String>();
        starringActors.add("Will Smith");
        generateAndPutTickets(5, "Mission Impossible", new Date(now + 86400000), TicketCategory.PREMIUM, "Warner Bros",
                starringActors, "Good film about smth.");
    }

    private void generateAndPutTickets(int count, String title, Date date, TicketCategory category,
                                       String studio, List<String> starringActors,
                                       String description) {
        long identiy;
        Ticket ticket;
        for (int i = 1; i < count + 1; i++) {
            identiy = getNextId();
            ticket = new Ticket(identiy, title, date, category, i, null, studio, starringActors, description);
            save(ticket);
            ticketCommandHandlers.handle(new CreateTicketCommand(title, date, category, i, studio,
                    starringActors, description, null, identiy, ticket.getId()));
        }
    }

    private synchronized long getNextId() {
        return id++;
    }
}
