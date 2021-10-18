package com.calculator1;

import com.helper.FileHelper;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class CalculatorEx {

    public static void main(String[] args) {
        CalculatorApp app = new CalculatorApp();
        app.run("src/main/resources/log.txt");
    }

}
