package com;

import java.util.Scanner;

public class Confirmation extends TextInput{


    public Confirmation(Scanner scanner, String prompt) {
        super(scanner, prompt, "O", "N", "o", "n");
    }
}
