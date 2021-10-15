package com.calculator1;

import com.helper.StringHelper;

public class Calculator {
    private final char[] OPERATORS = {'+', '-', '*', '/'};
    private final StringHelper stringHelper;

    public Calculator() {
        stringHelper = new StringHelper();
    }

    public double calcalate(String input) throws Exception {
        input = stringHelper.cleanSpace(input);
        input = addPriority(input);
        SubString subString;
        while ((subString = stringHelper.getStringInsideChar(input, '('))!=null){
            double expressionResult = calculateAnExpression(subString.getSubString());
            String tmp = input.substring(0, subString.getStartIndex())+expressionResult+input.substring(subString.getEndIndex());
            input = tmp;
        }
        return calculateAnExpression(input);
    }

    public boolean checkLessPriorityOperator(char c) {
        return c == '+' || c == '-';
    }

    public String addPriority (String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*' || s.charAt(i) == '/') {
                int oldLength = s.length();
                boolean flag1 = true;
                boolean flag2 = true;
                // run from i to the start of the String to add the "("
                // if we see the less priority operator or the start of the String
                for (int j1 = i + 1; j1 >= 0; j1--) {
                    // the start of the String
                    if (j1 == 0) {
                        s = "(" + s;
                        break;
                    }
                    // flag to ignore (..)
                    // Example: 1+(2+3)*4 -> 1+((2+3)*4) ignore (2+3)
                    if (s.charAt(j1) == ')') {
                        flag1 = false;
                    } else if (s.charAt(j1) == '(') {
                        flag1 = true;
                    }
                    //
                    if (flag1) {
                        if (checkLessPriorityOperator(s.charAt(j1))) {
                            s = s.substring(0, j1 + 1) + "(" + s.substring(j1 + 1);
                            break;
                        }
                    }
                }
                // after add "(" the position of i is changed
                i = i + (s.length() - oldLength);
                // run from i to the end of the String to add the ")"
                // if we see the less priority operator or the end of the String
                for (int j2 = i + 1; j2 < s.length(); j2++) {
                    if (j2 == s.length() - 1) {
                        s = s + ")";
                        break;
                    }
                    if (s.charAt(j2) == '(') {
                        flag2 = false;
                    } else if (s.charAt(j2) == ')') {
                        flag2 = true;
                    }
                    if (flag2) {
                        if (checkLessPriorityOperator(s.charAt(j2))) {
                            s = s.substring(0, j2) + ")" + s.substring(j2);
                            break;
                        }
                    }

                }
            }
        }
        return s;
    }

    // accepts numbers from -20 to 20
    private boolean checkNumberLimit(double number){
        return (number<=20) && (number>=-20);
    }

    private double parseDouble(String s) throws Exception {
        double number = Double.parseDouble(s);

        // if more than 20 or less -20 - returns a warning message.
        if (!checkNumberLimit(number)) throw new Exception("Accepts numbers from -20 to 20");

        return number;
    }

    public double calculateAnExpression(String input) throws Exception {
        double result = 0;
        int presentOperator = 0;
        for (int i = 0; i < input.length(); i++) {
            if (getOperator(input.charAt(i)) != -1) {
                if (i==0){
                    if (input.charAt(i)=='+'||input.charAt(i)=='-') {
                        continue;
                    } else {
                        throw new Exception("Operator position is wrong!");
                    }
                }

                double number = parseDouble(input.substring(0, i));
                result = calcalate(result, presentOperator, number);
                presentOperator = getOperator(input.charAt(i));
                input = input.substring(i + 1);
                i = 0;
            }
            if (i == input.length() - 1) {
                double number = parseDouble(input);
                result = calcalate(result, presentOperator, number);
            }
        }
        return result;
    }

    public double calcalate(double number1, int operator, double number2) throws Exception {
        switch (operator) {
            case 0:
                return number1 + number2;
            case 1:
                return number1 - number2;
            case 2:
                return number1 * number2;
            case 3:
                if (number2 == 0) {
                    throw new Exception("Can't divide with 0!");
                } else {
                    return number1 / number2;
                }
            default:
                throw new Exception("Operator unknown!");
        }
    }

    public int getOperator(char c) {
        for (int i = 0; i < OPERATORS.length; i++) {
            if (c == OPERATORS[i]) {
                return i;
            }
        }
        return -1;
    }

}
