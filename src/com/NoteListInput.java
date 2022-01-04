package com;

import java.util.ArrayList;
import java.util.Scanner;

public class NoteListInput {
    TextInput input;

    public NoteListInput(Scanner scanner, String prompt) {
        input = new TextInput(scanner, prompt);
    }

    public Float[] ask() {
        ArrayList<Float> notes = new ArrayList<>();
        boolean error;
        do {
            try {
                for (String s : input.ask().split(" ")) {
                    notes.add(Float.valueOf(s));
                }
                error = false;
            } catch (NumberFormatException e) {
                System.err.println("Saisie erronnée");
                error = true;
            }
        } while (error);
        return notes.toArray(new Float[notes.size()]);
    }
}
