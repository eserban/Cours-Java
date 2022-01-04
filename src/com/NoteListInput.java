package com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class NoteListInput {

    TextInput input;

    public NoteListInput(Scanner scanner, String prompt) {
        this.input = new TextInput(scanner, prompt);
    }


    public Float[] ask(){
        ArrayList<Float> notes = new ArrayList<>();
        boolean error;
        do {
            try{
                for (String s : input.ask().split(" ")) {
                    notes.add(Float.valueOf(s));
                }
                error = false;
            } catch (NumberFormatException e) {
                System.err.println("Saisie erronnée");
                error = true;
            }

        }while(error);


        return notes.toArray(new Float[notes.size()]);
    }

    //    for (String s : input.ask().split(" ")) {
//        student.getNotes().add(Float.valueOf(s));
//    }
}
