package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.user;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.UserCqrsRepositoryImpl;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCommandHandlers {

    @Autowired
    private UserCqrsRepositoryImpl userCqrsRepositoryImpl;


    public void handle(final CreateUserCommand command){
        final User user = new User(command.getName());
        userCqrsRepositoryImpl.store(user);
    }


}
