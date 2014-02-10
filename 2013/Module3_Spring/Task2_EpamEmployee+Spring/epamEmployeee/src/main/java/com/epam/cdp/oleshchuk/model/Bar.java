package com.epam.cdp.oleshchuk.model;

/**
 * User: Maksym_Oleshchuk
 */
public class Bar {
    private boolean animation;

    public Bar(boolean animation) {
        this.animation = animation;
    }

    public boolean isAnimation() {
        return animation;
    }

    public void setAnimation(boolean animation) {
        this.animation = animation;
    }
}
