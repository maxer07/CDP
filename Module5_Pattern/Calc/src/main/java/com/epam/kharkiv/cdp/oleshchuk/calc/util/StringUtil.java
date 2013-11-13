package com.epam.kharkiv.cdp.oleshchuk.calc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static String prepareInputString(String inputString) {
        inputString = inputString.replaceAll("\\s+", "");
        inputString = inputString.replaceAll("(\\(-)" , "(0-");
        if (inputString.startsWith("-")) {
            inputString = "0" + inputString;
        }
        inputString = prepareStringWithTrigonometry(inputString);
        return inputString;
    }

    private static String prepareStringWithTrigonometry(String inputString) {
        StringBuilder stringBuilder = new StringBuilder(inputString);
        Matcher m = Pattern.compile("(cos|sin|tg|ctg)\\((\\d+)\\)").matcher(stringBuilder.toString());
        while(m.find()){
            String trigonoFunc =  m.group(1);
            String angle = m.group(2);
            String substring = angle + "\\" + trigonoFunc + "\\";
            stringBuilder.delete(m.start(), m.end());
            stringBuilder.insert(m.start(), substring);
        }

        return stringBuilder.toString();
    }

}
