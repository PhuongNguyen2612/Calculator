package com.calculator1;

public class SubString {
    private String subString;
    private int startIndex;
    private int endIndex;

    public SubString(String subString, int startIndex, int endIndex) {
        this.subString = subString;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public String getSubString() {
        return subString;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}
