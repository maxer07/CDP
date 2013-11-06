package com.epam.kharkiv.cdp.oleshchuk.calc.model;

import com.epam.kharkiv.cdp.oleshchuk.calc.exception.CalcException;

public enum Sign {

    PLUS("+", 1),
    MINUS ("-", 1),
    DIV("/", 2),
    MULT("*", 2),
    LEFT("(", 0),
    RIGHT(")", 0);

    private String value;
    private int priority;

    private Sign(String value, int priority){
        this.value = value;
        this.priority = priority;
    }

    public String getValue() {
        return value;
    }

    public int getPriority() {
        return priority;
    }

    public static Sign getSignFromString(String sign) {
        if (sign.equals("+")) {
            return Sign.PLUS;
        } else if (sign.equals("-")) {
            return Sign.MINUS;
        } else if (sign.equals("*")) {
            return Sign.MULT;
        } else if (sign.equals("/")) {
            return Sign.DIV;
        } else if (sign.equals("(")) {
            return Sign.LEFT;
        } else if (sign.equals(")")) {
            return Sign.RIGHT;
        } else {
            throw new CalcException("No such sign");
        }
    }
}
