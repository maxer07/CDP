package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.user;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.Command;

public class CreateUserCommand implements Command {
    private String name;

    public CreateUserCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
