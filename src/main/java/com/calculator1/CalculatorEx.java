package com.calculator1;

import com.helper.FileHelper;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class CalculatorEx {

    /**
     * Application entry point.
     *
     * @param args application command line arguments
     */
    public static void main(String[] args) {
        args = new String[]{"-f", "src/main/resources/log.txt"};
        CalculatorApp app = new CalculatorApp();
        app.run(args);
    }
}
