package com.epam.kharkiv.cdp.oleshchuk.calc.operation.arithmetic;

import com.epam.kharkiv.cdp.oleshchuk.calc.operation.MathOperation;

import java.math.BigDecimal;
import java.math.MathContext;

public class DivOperation extends MathOperation {

    @Override
    public BigDecimal makeCalc() {
        return x.divide(y, MathContext.DECIMAL32);
    }

}
