package com.epam.kharkiv.cdp.oleshchuk.calc.util;

public class StringUtil {

    public static String prepareInputString(String inputString) {
        inputString = inputString.replaceAll("\\s+", "");
        inputString = inputString.replaceAll("(\\(-)" , "(0-");
        if (inputString.startsWith("-")) {
            inputString = "0" + inputString;
        }
        return inputString;
    }

}
