package com.epam.kharkiv.cdp.oleshchuk.calc.algorithm;

import com.epam.kharkiv.cdp.oleshchuk.calc.exception.CalcException;
import com.epam.kharkiv.cdp.oleshchuk.calc.model.Associativity;
import com.epam.kharkiv.cdp.oleshchuk.calc.model.Sign;
import com.epam.kharkiv.cdp.oleshchuk.calc.util.ListUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class ShuntingYard {

    public static String calculate(String inputString) {
        Stack<String> valuesStack = parseString(inputString);
        if (!valuesStack.empty() && valuesStack.size() == 1) {
            return (valuesStack.pop() + "0");
        }
        DecimalFormat dtime = new DecimalFormat("0.00");
        BigDecimal result = getResult(valuesStack);
        return dtime.format(result).replaceAll("\\,", ".");
    }

    private static Stack<String> parseString(String inputString) {
        Stack<String> numbersStack = new Stack<String>();
        Stack<Sign> signsStack = new Stack<Sign>();
        StringTokenizer stringTokenizer = new StringTokenizer(inputString, "+-/*()^!\\", true);
        while (stringTokenizer.hasMoreElements()) {
            String token = stringTokenizer.nextElement().toString();
            try {
                parseNumberAndPushItIntoStack(token, numbersStack);
            } catch (NumberFormatException e) {
                pushSignIntoSignStack(token, numbersStack, signsStack);
            }
        }
        while (!signsStack.empty()) {
            numbersStack.add(signsStack.pop().getValue());
        }
        return ListUtil.createStackFromList(numbersStack);
    }

    private static void pushSign(List<String> numbersStack, Stack<Sign> signsStack, Sign sign) {
        if (isCurrentSignPriorityLessStackSignPriority(sign, signsStack)) {
            numbersStack.add(signsStack.pop().getValue());
            pushSign(numbersStack, signsStack, sign);
        } else {
            signsStack.push(sign);
        }
    }


    private static BigDecimal getResult(Stack<String> sourceStack) {
        Stack<String> numbersStack = new Stack<String>();
        BigDecimal result = BigDecimal.ZERO;
        while (!sourceStack.empty()) {
            String token = sourceStack.pop();
            try {
                parseNumberAndPushItIntoStack(token, numbersStack);
            } catch (NumberFormatException e) {
                result = doCalculation(token, numbersStack);
                numbersStack.push(result.toString());
            }
        }
        if (numbersStack.size() != 1) {
            throw new CalcException("Too many numbers");
        }
        return result;
    }

    private static void parseNumberAndPushItIntoStack(String token, Stack<String> numbersStack) {
        Double number = Double.valueOf(token);
        numbersStack.push(number.toString());
    }

    private static void pushSignIntoSignStack(String operator, Stack<String> numbersStack, Stack<Sign> signsStack) {
        if (operator.equals("\\")) return;
        Sign sign = Sign.getSignFromString(operator);
        if (sign == Sign.FACTORIAL) {
            numbersStack.add(sign.getValue());
        } else if (sign == Sign.LEFT) {
            signsStack.push(sign);
        } else if (sign == Sign.RIGHT) {
            while (signsStack.peek() != Sign.LEFT) {
                numbersStack.add(signsStack.pop().getValue());
            }
            signsStack.pop();
        } else {
            pushSign(numbersStack, signsStack, sign);
        }
    }

    private static boolean isCurrentSignPriorityLessStackSignPriority(Sign sign, Stack<Sign> signsStack) {
        return (!signsStack.empty() && (signsStack.peek().getPriority() >= sign.getPriority()) && sign.getAssociativity() == Associativity.LEFT);
    }

    private static BigDecimal doCalculation(String operator, Stack<String> numbersStack) {
        Double lastNumber = Double.valueOf(numbersStack.pop());
        Sign sign = Sign.getSignFromString(operator);
        if (sign.isUnary()) {
            return sign.getMathOperation().doOperation(new BigDecimal(lastNumber));
        } else {
            Double preLastNumber = Double.valueOf(numbersStack.pop());
            return sign.getMathOperation().doOperation(new BigDecimal(preLastNumber), new BigDecimal(lastNumber));
        }

    }


}
