package com.epam.kharkiv.cdp.oleshchuk.cinema.model;

public class TicketsFilterParams {
    private String title;
    private String category;
    private String dateFrom;
    private String dateTo;

    public TicketsFilterParams(String title, String category, String dateFrom, String dateTo) {
        this.title = title;
        this.category = category;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
