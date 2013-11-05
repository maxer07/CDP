package com.epam.kharkiv.cdp.oleshchuk.calc.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ListUtil {

    public static Stack<String> createStackFromList(List<String> numbersStack) {
        List<String> list = new ArrayList<String>(numbersStack);
        Stack<String> reversedStack = new Stack<String>();
        Collections.reverse(list);
        reversedStack.addAll(list);
        return reversedStack;
    }
}
