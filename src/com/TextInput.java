package com;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class TextInput {
    private Scanner scanner;
    private String prompt;
    private HashSet<String> allowedValues;

    public TextInput(Scanner scanner, String prompt, String... allowedValues) {
        this.allowedValues = new HashSet<>();
        for (String s : allowedValues) {
            this.allowedValues.add(s);
        }
        this.scanner = scanner;
        this.prompt = prompt;
    }

    public String ask() {
        String command;

        do {
            System.out.println(prompt);
            command = scanner.nextLine();
        } while (allowedValues.size() > 0 && !allowedValues.contains(command));

        return command;
    }
}
