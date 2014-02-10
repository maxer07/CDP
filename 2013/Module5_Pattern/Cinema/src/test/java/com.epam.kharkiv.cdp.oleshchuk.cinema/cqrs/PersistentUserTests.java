/*
 * Copyright 2010 Matt Givney
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.Event;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.store.EventStore;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.user.UserCreatedEvent;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.EventModel;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.TicketCategory;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/resources/spring-config.xml")
public class PersistentUserTests {

    @Autowired
    private UserCqrsRepositoryImpl userCqrsRepository;
    @Autowired
    private TicketCqrsRepositoryImpl ticketCqrsRepository;
    @Autowired
    private EventStore eventStore;
    @Autowired
    MongoTemplate mongoTemplate;



    @Test
    public void testStoreEvents(){
        long uuid = -100L;
        User user = new User(uuid, "testUser");
        userCqrsRepository.store(user);
        List<Event> events = eventStore.sourceEvents(uuid);
        assertEquals(1, events.size());
        assertTrue(events.get(0) instanceof UserCreatedEvent);
        mongoTemplate.remove(new BasicQuery(" {identity : " + uuid + "}"), EventModel.class);
    }


    @Test
    public void testLoadContactByEvents(){
        long uuid = -101L;
        userCqrsRepository.store(new User(uuid, "testUser2"));
        final User user = userCqrsRepository.load(uuid);
        assertEquals("testUser2", user.getName());
        mongoTemplate.remove(new BasicQuery(" {identity : " + uuid + "}"), EventModel.class);
    }

    @Test
    public void testStoreTicketByEvents(){
        long uuid = -103L;
        String title = "very bad film";
        String description = "no description";
        String studio = "Ukrainian studio";
        Date date = new Date(System.currentTimeMillis());
        Integer place = 1;
        TicketCategory ticketCategory = TicketCategory.BAR;
        User user = new User("Georgii Rychko");
        ticketCqrsRepository.store(new Ticket(uuid, title, date, ticketCategory,place,user,studio, null,description));
        final Ticket ticket = ticketCqrsRepository.load(uuid);
        assertEquals(title, ticket.getTitle());
        assertEquals(description, ticket.getDescription());
        assertEquals(studio, ticket.getStudio());
        assertEquals(date, ticket.getDate());
        assertEquals(place, ticket.getPlace());
        assertEquals(ticketCategory, ticket.getCategory());
        assertEquals(user.getName(), ticket.getUser().getName());
        mongoTemplate.remove(new BasicQuery(" {identity : " + uuid + "}"), EventModel.class);
    }



}
