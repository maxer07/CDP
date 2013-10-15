package com.epam.cdp.oleshchuk.cinema.util;

public final class Constant {

    public static final String SQL__USER_TABLE = "user";
    public static final String SQL__TICKET_TABLE = "ticket";

    public static final String SQL__USER_TABLE__RAW_ID = "id";
    public static final String SQL__USER_TABLE__RAW_NAME = "name";

    public static final String SQL__TICKET_TABLE__RAW_ID = "id";
    public static final String SQL__TICKET_TABLE__RAW_TITLE = "title";
    public static final String SQL__TICKET_TABLE__RAW_DATE = "film_date";
    public static final String SQL__TICKET_TABLE__RAW_CATEGORY = "category";
    public static final String SQL__TICKET_TABLE__RAW_PLACE = "place";
    public static final String SQL__TICKET_TABLE__RAW_USER_ID = "user_id";

    public static final String SQL__QUERY__USER_GET_BY_ID = "SELECT * FROM " + SQL__USER_TABLE + " WHERE id = ?";
    public static final String SQL__QUERY__USER_GET_ALL = "SELECT * FROM " + SQL__USER_TABLE;

    public static final String SQL__QUERY__TICKET_GET_ALL = "SELECT * FROM " + SQL__TICKET_TABLE + " WHERE user_id is NULL";
    public static final String SQL__QUERY__TICKET_GET_BY_TICKET_ID = "SELECT * FROM " + SQL__TICKET_TABLE + " WHERE id = ?";
    public static final String SQL__QUERY__TICKET_GET_BY_USER = "SELECT * FROM " + SQL__TICKET_TABLE + " WHERE user_id = ?";
    public static final String SQL__QUERY__TICKET_BOOK_BY_USER = "UPDATE " + SQL__TICKET_TABLE + " SET user_id = ? WHERE id = ?";
    public static final String SQL__QUERY__TICKET_GET_BOOKED_BY_TICKET_IDS = "SELECT * FROM ticket WHERE (user_id is NOT NULL) AND (id IN (?))";

}
