package com.epam.kharkiv.cdp.oleshchuk.calc.operation.trigonometry;

import java.math.BigDecimal;

public class CosOperation extends TrigonometricOperation {

    @Override
    protected BigDecimal makeCalc() {
        return new BigDecimal(Math.cos(getRadiansFromAngle()));
    }

}
