package com.calculator1;

import com.helper.FileHelper;
import org.apache.commons.cli.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class CalculatorApp {

    public void run(String[] args) {

        CommandLine line = parseArguments(args);

        if (line.hasOption("filename")) {

            System.out.println(line.getOptionValue("filename"));
            String fileName = line.getOptionValue("filename");

            FileHelper file = initialLogFile(fileName);
            startApp(file);

        } else {
            printAppHelp();
        }
    }

    public FileHelper initialLogFile(String fileName) {
        FileHelper fileHelper;
        try {
            fileHelper = new FileHelper(fileName);
            return fileHelper;
        } catch (FileNotFoundException e) {
            printAppHelp();
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
            if (input.equalsIgnoreCase("end")){
                break;
            } else {
                try {
                    double result = calculator.calcalate(input);
                    System.out.print( " = "+result+"\n");
                    String message = input + " = "+result;
                    logFile.writeToFile(message);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    logFile.writeToFile(input + "\tError! "+e.getMessage());
                }
            }
        }
    }

    /**
     * Parses application arguments
     *
     * @param args application arguments
     * @return <code>CommandLine</code> which represents a list of application
     * arguments.
     */
    private CommandLine parseArguments(String[] args) {

        Options options = getOptions();
        CommandLine line = null;

        CommandLineParser parser = new DefaultParser();

        try {
            line = parser.parse(options, args);

        } catch (ParseException ex) {

            System.err.println("Failed to parse command line arguments");
            System.err.println(ex.toString());
            printAppHelp();

            System.exit(1);
        }

        return line;
    }

    /**
     * Generates application command line options
     *
     * @return application <code>Options</code>
     */
    private Options getOptions() {

        Options options = new Options();

        options.addOption("f", "filename", true, "file to log history data");
        return options;
    }

    /**
     * Prints application help
     */
    private void printAppHelp() {

        Options options = getOptions();

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("CalculatorEx", options, true);
    }

}
