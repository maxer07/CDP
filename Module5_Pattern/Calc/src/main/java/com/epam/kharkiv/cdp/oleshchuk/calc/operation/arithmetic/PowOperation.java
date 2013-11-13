package com.epam.kharkiv.cdp.oleshchuk.calc.operation.arithmetic;

import com.epam.kharkiv.cdp.oleshchuk.calc.operation.MathOperation;

import java.math.BigDecimal;
import java.math.MathContext;

public class PowOperation extends MathOperation {

    @Override
    public BigDecimal makeCalc() {
        return x.pow(y.intValue(), MathContext.DECIMAL32);
    }

}
