package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.user;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.Command;
import org.apache.commons.lang3.Validate;


public class UserCommand implements Command {

    private Long userId;

    public UserCommand(Long userId){
        Validate.notNull(userId, "User Id is required");
        this.userId = userId;
    }

    public Long getUserId(){
        return this.userId;
    }
    
}
