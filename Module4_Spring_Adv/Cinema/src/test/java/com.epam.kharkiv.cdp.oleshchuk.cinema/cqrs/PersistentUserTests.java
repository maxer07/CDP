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
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.EventStore;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.MemoryEventStore;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.UserCreatedEvent;
import com.epam.kharkiv.cdp.oleshchuk.cinema.exception.CqrsVersionException;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.EventModel;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/resources/spring-config.xml")
public class PersistentUserTests {

    @Autowired
    private UserRepository persistentUsers;
    @Autowired
    private EventStore eventStore;
    @Autowired
    MongoTemplate mongoTemplate;



    @Test
    public void testStoreEvents(){
        long uuid = 20L;
        User user = new User(uuid, "testUser");
        persistentUsers.store(user);
        List<Event> events = eventStore.sourceEvents(uuid);
        assertEquals(1, events.size());
        assertTrue(events.get(0) instanceof UserCreatedEvent);
        mongoTemplate.remove(new BasicQuery(" {identity : " + uuid + "}"), EventModel.class);
    }

    @Test
    public void testLoadContactByEvents(){
        long uuid = 21L;
        persistentUsers.store(new User(uuid, "testUser2"));
        final User user = persistentUsers.load(21L);
        assertEquals("testUser2", user.getName());
        mongoTemplate.remove(new BasicQuery(" {identity : " + uuid + "}"), EventModel.class);
    }

}
