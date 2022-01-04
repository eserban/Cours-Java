package com;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

//Exercice 1 :
//Coment pourrait-on éviter de répéter la saisie complète
// des TextInput de type "O/N" (parce que ça oblige à écrire "O",
// "N" à chaque fois)
// input = new TextInput(scanner, "Voulez-vous ajouter des notes (O/N)? ", "O", "N", "o", "n");
// input = new TextInput(scanner, "Cet étudiant est inconnu. Voulez-vous le créer (O/N)? ", "O", "N", "o", "n");

//Exercice 2 :
//Faire une classe NoteInput qui contrôle la saisie des notes

public class Application {
    public static <Toto> Toto getFirst(Toto... array) {
        return array[1];
    }

    public static void main(String[] args) {
        StudentMap students = new StudentMap();
        File file = new File("students.txt");

        try {
            students.load(file);
            System.out.println(students);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        Scanner scanner = new Scanner(System.in);
        TextInput command = new TextInput(scanner, ">");
        String name = command.ask();
        Student student;

        if (students.containsKey(name)) {
            student = students.get(name);
            System.out.println(String.format("Notes de %s : %s",
                    student.getFullName(),
                    student.getNotes()));
            askToAddNotes(scanner, student);
        } else {
            askToCreateNewStudent(students, scanner, name);
        }

        System.out.println(students);
    }

    private static void askToAddNotes(Scanner scanner, Student student) {
        Confirmation confirm;
        TextInput input;
        confirm = new Confirmation(scanner, "Voulez-vous ajouter des notes? ");
        if (confirm.askOK()) {
            input = new TextInput(scanner, "Saisir les notes séparées par des espaces >");
            for (String s : input.ask().split(" ")) {
                student.getNotes().add(Float.valueOf(s));
            }
        }
    }

    private static void askToCreateNewStudent(StudentMap students, Scanner scanner, String name) {
        Confirmation confirm = new Confirmation(scanner, "Cet étudiant est inconnu. Voulez-vous le créer? ");
        if (confirm.askOK()) {
            students.put(name, new Student(name));
        }
    }

    public static float[] maxN(float[] values, int count) {
        float[] max;

        // 1. trier le tableau
        Arrays.sort(values);
        // 2. Garder les "count" dernières valeurs
        max = Arrays.copyOfRange(values,
                values.length - count, values.length);

        return max;
    }
}
