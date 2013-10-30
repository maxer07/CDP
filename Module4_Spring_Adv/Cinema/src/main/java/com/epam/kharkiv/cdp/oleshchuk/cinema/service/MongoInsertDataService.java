package com.epam.kharkiv.cdp.oleshchuk.cinema.service;

import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.UserDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.nosql.TicketMongoDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.nosql.UserMongoDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.TicketCategory;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MongoInsertDataService implements InitializingBean {

    @Autowired
    @Qualifier(value = "userMongoDao")
    private UserMongoDao userDao;

    @Autowired
    @Qualifier(value = "ticketMongoDao")
    private TicketMongoDao ticketDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        deleteAllCollections();
        generateUsers();
        generateTickets();

    }

    private void deleteAllCollections() {
        userDao.dropCollection("ticket");
        userDao.dropCollection("user");
    }


    private void generateUsers() {
        User user = new User("max");
        userDao.save(user);
        user = new User("dima");
        userDao.save(user);
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
        Ticket ticket;
        for (int i = 1; i < count + 1; i++) {
            ticket = new Ticket(title, date, category, i, null, studio, starringActors, description);
            ticketDao.save(ticket);
        }
    }
}
