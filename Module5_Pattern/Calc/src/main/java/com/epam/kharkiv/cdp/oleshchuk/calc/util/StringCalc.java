package com.epam.kharkiv.cdp.oleshchuk.calc.util;

import com.epam.kharkiv.cdp.oleshchuk.calc.exception.CalcException;

public class StringCalc {

    public static Double calculate (double x, double y, String sign) {
        if (sign.equals("+")) {
            return x+y;
        }
        else if (sign.equals("-")) {
            return x-y;
        }
        else if (sign.equals("*")) {
            return x*y;
        }
        else if (sign.equals("/")) {
            return x/y;
        }
        else  {
            throw new CalcException("Sign is unsupported " + sign);
        }
    }
}
