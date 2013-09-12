package com.epam.cdp.oleshchuk.model;

/**
 * User: Maksym_Oleshchuk
 */
public class Legend {
    private boolean reversed;
    private String verticalAlign;
    private int x;
    int y;

    public Legend(boolean reversed, String verticalAlign, int x, int y) {
        this.reversed = reversed;
        this.verticalAlign = verticalAlign;
        this.x = x;
        this.y = y;
    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    public String getVerticalAlign() {
        return verticalAlign;
    }

    public void setVerticalAlign(String verticalAlign) {
        this.verticalAlign = verticalAlign;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
