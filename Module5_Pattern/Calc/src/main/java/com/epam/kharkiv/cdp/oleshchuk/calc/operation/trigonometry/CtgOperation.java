package com.epam.kharkiv.cdp.oleshchuk.calc.operation.trigonometry;

import java.math.BigDecimal;

public class CtgOperation extends TrigonometricOperation {

    @Override
    protected BigDecimal makeCalc() {
        return new BigDecimal(1/Math.tan(getRadiansFromAngle()));
    }

}
