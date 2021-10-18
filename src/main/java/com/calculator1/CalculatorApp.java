package com.calculator1;

import com.helper.FileHelper;
import org.apache.commons.cli.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class CalculatorApp {

    public void run(String fileName) {
        FileHelper file = initialLogFile(fileName);
        startApp(file);
    }

    public FileHelper initialLogFile(String fileName) {
        FileHelper fileHelper;
        try {
            fileHelper = new FileHelper(fileName);
            return fileHelper;
        } catch (FileNotFoundException e) {
            System.out.println("Can't find your file!");
        }
        return null;
    }

    public void startApp(FileHelper logFile) {
        Calculator calculator = new Calculator();
        boolean end = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("* Type 'end' to close the calculator.\n* ");
        while (!end) {
            System.out.print("Input: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("end")) {
                break;
            } else {
                try {
                    double result = calculator.calcalate(input);
                    System.out.print(" = " + result + "\n");
                    String message = input + " = " + result;
                    logFile.writeToFile(message);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    logFile.writeToFile(input + "\tError! " + e.getMessage());
                }
            }
        }
    }

}
