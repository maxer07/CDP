package com.epam.cdp.oleshchuk.model;

/**
 * User: Maksym_Oleshchuk
 */
public class PlotOptions {

    private Bar bar;

    public PlotOptions(Bar bar) {
        this.bar = bar;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }
}
