package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs;

import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository {
    void store(Ticket ticket);
    Ticket load(Long aggregateId);
}
