package com.epam.kharkiv.cdp.oleshchuk.calc.operation.arithmetic;

import com.epam.kharkiv.cdp.oleshchuk.calc.operation.MathOperation;

import java.math.BigDecimal;

public class AddOperation extends MathOperation {

    @Override
    protected BigDecimal makeCalc() {
        return x.add(y);
    }

}
