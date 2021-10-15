package com.helper;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileHelper {
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private String fileName;

    public FileHelper(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        printWriter = new PrintWriter(fileName);
    }

    public String getDateTime() {
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }

    public String formatMessage(String message) {
        return getDateTime() + "\t\t|\t\t" + message;
    }

    public String writeToFile(String messages) {
        messages = formatMessage(messages);
        System.out.println(messages);
        printWriter.println(messages);
        printWriter.flush();
        return messages;
    }


}
