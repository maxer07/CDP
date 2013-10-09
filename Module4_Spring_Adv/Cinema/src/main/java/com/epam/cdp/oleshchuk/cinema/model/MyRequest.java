package com.epam.cdp.oleshchuk.cinema.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAKSYM_OLESHCHUK
 * Date: 07.10.13
 * Time: 16:03
 * To change this template use File | Settings | File Templates.
 */
public class MyRequest implements Serializable{
//    {ticketIds : [1,2,3,4,5] , userId : 10}
    private Long userId;
    private Long ticketIds;

    public MyRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTicketIds() {
        return ticketIds;
    }

    public void setTicketIds(Long ticketIds) {
        this.ticketIds = ticketIds;
    }
}
