package com.epam.kharkiv.cdp.oleshchuk.calc.operation.arithmetic;

import com.epam.kharkiv.cdp.oleshchuk.calc.operation.MathOperation;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FactorialOperation extends MathOperation {

    @Override
    public BigDecimal makeCalc() {
        if (x.signum() == 0) return BigDecimal.ONE;
        BigDecimal decrementBigInteger = x.subtract(BigDecimal.ONE);
        return x.multiply(doOperation(decrementBigInteger, null));
    }

}
