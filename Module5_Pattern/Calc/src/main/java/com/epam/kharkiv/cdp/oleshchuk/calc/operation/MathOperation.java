package com.epam.kharkiv.cdp.oleshchuk.calc.operation;

import java.math.BigDecimal;

public abstract class MathOperation {

    protected BigDecimal x;
    protected BigDecimal y;

    public BigDecimal doOperation(BigDecimal...args){
        this.x = args[0];
        if (args.length >1) {
            this.y = args[1];
        }
        return makeCalc();
    }

    protected abstract BigDecimal makeCalc();
}
