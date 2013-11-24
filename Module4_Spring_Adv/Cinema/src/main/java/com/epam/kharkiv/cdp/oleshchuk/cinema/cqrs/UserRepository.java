package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs;

import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository {
    void store(User user);
    User load(Long aggregateId);
}
