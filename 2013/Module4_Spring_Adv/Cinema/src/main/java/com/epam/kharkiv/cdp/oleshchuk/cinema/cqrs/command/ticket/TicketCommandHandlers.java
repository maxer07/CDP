package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.ticket;
import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.TicketCqrsRepositoryImpl;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketCommandHandlers {

    @Autowired
    private TicketCqrsRepositoryImpl ticketCqrsRepositoryImpl;


    public void handle(final CreateTicketCommand command){
        final Ticket ticket = new Ticket(command.getIdentity(), command.getTitle(), command.getDate(), command.getCategory(),command.getPlace(),
                command.getUser(), command.getStudio(), command.getStarringActors(), command.getDescription());
        ticketCqrsRepositoryImpl.store(ticket);
    }

    public void handle(final TicketBookedCommand command){
        final Ticket ticket = ticketCqrsRepositoryImpl.load(command.getTicketId());
        ticket.bookTicket(command.getUser());
        ticketCqrsRepositoryImpl.store(ticket);
    }


}
