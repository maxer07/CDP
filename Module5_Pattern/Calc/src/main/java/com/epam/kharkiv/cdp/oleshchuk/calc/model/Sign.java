package com.epam.kharkiv.cdp.oleshchuk.calc.model;

import com.epam.kharkiv.cdp.oleshchuk.calc.exception.CalcException;
import com.epam.kharkiv.cdp.oleshchuk.calc.operation.*;
import com.epam.kharkiv.cdp.oleshchuk.calc.operation.arithmetic.*;
import com.epam.kharkiv.cdp.oleshchuk.calc.operation.trigonometry.CosOperation;
import com.epam.kharkiv.cdp.oleshchuk.calc.operation.trigonometry.CtgOperation;
import com.epam.kharkiv.cdp.oleshchuk.calc.operation.trigonometry.SinOperation;
import com.epam.kharkiv.cdp.oleshchuk.calc.operation.trigonometry.TgOperation;

public enum Sign {

    PLUS("+", 1, Associativity.LEFT, AddOperation.class, false),
    MINUS ("-", 1, Associativity.LEFT, SubOperation.class, false),
    DIV("/", 2, Associativity.LEFT, DivOperation.class, false),
    MULT("*", 2, Associativity.LEFT, MultOperation.class, false),
    POW("^", 3, Associativity.RIGHT, PowOperation.class,false),
    FACTORIAL("!", 4, Associativity.LEFT, FactorialOperation.class, true),
    SIN("sin", 4, Associativity.LEFT, SinOperation.class,true),
    COS("cos", 4, Associativity.LEFT, CosOperation.class,true),
    TG("tg", 4, Associativity.LEFT, TgOperation.class, true),
    CTG("ctg", 4, Associativity.LEFT, CtgOperation.class,true),
    LEFT("(", 0, Associativity.LEFT, null, false),
    RIGHT(")", 0, Associativity.LEFT, null, false);

    private String value;
    private int priority;
    private Associativity associativity;
    private MathOperation mathOperation;
    private boolean unary;

    private Sign(String value, int priority, Associativity associativity, Class<? extends MathOperation> clazz, boolean unary) {
        this.value = value;
        this.priority = priority;
        this.associativity = associativity;
        this.unary = unary;
        try {
            if (priority!=0) this.mathOperation = (MathOperation) Class.forName(clazz.getName()).newInstance();
        } catch (Exception e) {
            throw new CalcException("There is no class with name " + clazz.getName());
        }

    }

    public String getValue() {
        return value;
    }

    public int getPriority() {
        return priority;
    }

    public Associativity getAssociativity() {
        return associativity;
    }

    public boolean isUnary() {
        return unary;
    }

    public MathOperation getMathOperation() {
        return mathOperation;
    }

    public static Sign getSignFromString(String sign) {
        switch (sign) {
            case "+" : return Sign.PLUS;
            case "-" : return Sign.MINUS;
            case "*" : return Sign.MULT;
            case "/" : return Sign.DIV;
            case "(" : return Sign.LEFT;
            case ")" : return Sign.RIGHT;
            case "^" : return Sign.POW;
            case "!" : return Sign.FACTORIAL;
            case "sin" : return Sign.SIN;
            case "cos" : return Sign.COS;
            case "tg" : return Sign.TG;
            case "ctg" : return Sign.CTG;
            default: throw new CalcException("No such sign");
        }


    }
}
