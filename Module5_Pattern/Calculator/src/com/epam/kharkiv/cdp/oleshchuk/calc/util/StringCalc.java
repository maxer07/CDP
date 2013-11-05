package com.epam.kharkiv.cdp.oleshchuk.calc.util;

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
            throw new RuntimeException("Sign is unssuported " + sign);
        }
    }
}
