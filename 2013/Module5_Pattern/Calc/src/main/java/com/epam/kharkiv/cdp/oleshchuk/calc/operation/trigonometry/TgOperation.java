package com.epam.kharkiv.cdp.oleshchuk.calc.operation.trigonometry;

import java.math.BigDecimal;

public class TgOperation extends TrigonometricOperation {

    @Override
    protected BigDecimal makeCalc() {
        return new BigDecimal(Math.tan(getRadiansFromAngle()));
    }

}
