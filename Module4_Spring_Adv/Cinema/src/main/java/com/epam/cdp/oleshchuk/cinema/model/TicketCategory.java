package com.epam.cdp.oleshchuk.cinema.model;

public enum TicketCategory {
    STANDART(1), PREMIUM(2), BAR(3);

    private final int priority;

    private TicketCategory(int prior) {
        priority = prior;
    }

    public int getPriority() {
        return priority;
    }
}
