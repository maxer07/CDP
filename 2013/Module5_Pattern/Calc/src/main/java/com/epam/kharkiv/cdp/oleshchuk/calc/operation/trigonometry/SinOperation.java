package com.epam.kharkiv.cdp.oleshchuk.calc.operation.trigonometry;

import java.math.BigDecimal;

public class SinOperation extends TrigonometricOperation {

    @Override
    protected BigDecimal makeCalc() {
        return new BigDecimal(Math.sin(getRadiansFromAngle()));
    }

}
