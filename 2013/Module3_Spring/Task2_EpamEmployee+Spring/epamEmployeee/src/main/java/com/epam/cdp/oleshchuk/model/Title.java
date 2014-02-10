package com.epam.cdp.oleshchuk.model;


public class Title {
    private String text;
    private Integer margin;

    public Title(String text, Integer margin) {
        this.text = text;
        this.margin = margin;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }
}
