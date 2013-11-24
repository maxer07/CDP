package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.ticket;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.TicketRepository;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.UserRepository;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.user.CreateUserCommand;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketCommandHandlers {

    @Autowired
    private TicketRepository ticketRepository;


    public void handle(final CreateTicketCommand command){
        final Ticket ticket = new Ticket(command.getIdentity(), command.getTitle(), command.getDate(), command.getCategory(),command.getPlace(),
                command.getUser(), command.getStudio(), command.getStarringActors(), command.getDescription());
        ticketRepository.store(ticket);
    }

    public void handle(final TicketBookedCommand command){
        final Ticket ticket = ticketRepository.load(command.getTicketId());
        ticket.bookTicket(command.getUser());
        ticketRepository.store(ticket);
    }


}
