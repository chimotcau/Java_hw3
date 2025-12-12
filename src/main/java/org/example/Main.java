package org.example;

import org.example.parser.JsonParser;
import org.example.model.Document;

public class Main {
    public static void main(String[] args) {
        String file_name = args[0];

        try {
            Document document = JsonParser.parseDocument(file_name);

            System.out.println("Class: " + document.getClass());
            System.out.println(document.toString());

        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}