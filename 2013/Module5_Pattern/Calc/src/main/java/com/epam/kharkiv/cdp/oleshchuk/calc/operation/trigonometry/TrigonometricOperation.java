package com.epam.kharkiv.cdp.oleshchuk.calc.operation.trigonometry;

import com.epam.kharkiv.cdp.oleshchuk.calc.operation.MathOperation;

public abstract class TrigonometricOperation extends MathOperation {


    protected double getRadiansFromAngle() {
        return Math.toRadians(x.doubleValue());
    }

}
