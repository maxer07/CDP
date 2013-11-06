package com.epam.kharkiv.cdp.oleshchuk.calc;


import com.epam.kharkiv.cdp.oleshchuk.calc.algorithm.ShuntingYard;
import com.epam.kharkiv.cdp.oleshchuk.calc.util.StringUtil;

public class Runner {

    public static void main(String[] args) {
        try{
        final String input = System.getenv("input");
        String prepareString = StringUtil.prepareInputString(input);
        String result = ShuntingYard.calculate(prepareString);
        System.out.println(result);
        } catch (Exception e) {
            System.out.println("FAIL");
        }
    }

}
