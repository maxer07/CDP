package com.epam.kharkiv.cdp.oleshchuk.cinema.model;

import java.util.List;

public class TicketsFilterParams {
    private String title;
    private String category;
    private String dateFrom;
    private String dateTo;
    private String studio;
    private List<String> starringActors;

    public TicketsFilterParams(String title, String category, String dateFrom, String dateTo, String studio, List<String> starringActors) {
        this.title = title;
        this.category = category;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.studio = studio;
        this.starringActors = starringActors;
    }

    public List<String> getStarringActors() {
        return starringActors;
    }

    public void setStarringActors(List<String> starringActors) {
        this.starringActors = starringActors;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
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

    public boolean allParamsIsNull() {
        return ( (title== null) && (category == null) && (dateFrom == null)
        && (dateTo == null) && (studio == null));
    }
}
