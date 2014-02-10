package com.epam.kharkiv.cdp.oleshchuk.calc.operation.arithmetic;

import com.epam.kharkiv.cdp.oleshchuk.calc.operation.MathOperation;

import java.math.BigDecimal;

public class SubOperation extends MathOperation {

    @Override
    public BigDecimal makeCalc() {
        return x.subtract(y);
    }

}
