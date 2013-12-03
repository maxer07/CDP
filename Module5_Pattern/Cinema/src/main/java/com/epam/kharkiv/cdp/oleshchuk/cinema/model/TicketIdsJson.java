package com.epam.kharkiv.cdp.oleshchuk.cinema.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TicketIdsJson implements Serializable {

    private List<String> ticketIds;

    public List<BigInteger> getTicketIds() {
        List<BigInteger> bigIntegers = new ArrayList<BigInteger>();
        for (String ticketId : ticketIds) {
            bigIntegers.add(new BigInteger(ticketId, 10));
        }
        return bigIntegers;
    }

    @Override
    public String toString() {
        return "RequestJson{" +
                "ticketIds=" + ticketIds +
                '}';
    }
}
