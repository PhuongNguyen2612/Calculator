package com.helper;

import com.calculator1.SubString;

public class StringHelper {

    public String cleanSpace(String s) {
        return s.replaceAll("\\s","");
    }

    public SubString getStringInsideChar(String s, char c) throws Exception {
        if (!checkParentheses(s, c)) throw new Exception("Parentheses is wrong!");
        int startIndex = s.lastIndexOf(c);
        if (startIndex < 0) {
            return null;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (s.charAt(i) == getReverseBracket(c)) {
                return new SubString(s.substring(startIndex+1, i), startIndex, i+1);
            }
        }
        return null;
    }

    public boolean checkParentheses(String s, char c) {
        int countChar = 0;
        int countReverseChar = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                countChar++;
            }
            if (s.charAt(i) == getReverseBracket(c)) {
                countReverseChar++;
            }
        }
        return countChar == countReverseChar;
    }

    public char getReverseBracket(char c) {
        return c == '(' ? ')' :
                c == '[' ? ']' :
                        c == '{' ? '}' :
                                c == ')' ? '(' :
                                        c == ']' ? '[' :
                                                c == '}' ? '{' : c;

    }
}
