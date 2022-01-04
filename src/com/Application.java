package com;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Application {

    public static void main(String[] args) {
        StudentMap students = new StudentMap();
        File file = new File("students.txt");

        try {
            students.load(file);
            System.out.println(students);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        SingletonScanner singletonScanner = SingletonScanner.getInstance();
        TextInput command = new TextInput(singletonScanner.getScanner(), ">");
        String name = command.ask();
        Student student;



        if (students.containsKey(name)) {
            student = students.get(name);
            System.out.println(String.format("Notes de %s : %s",
                    student.getFullName(),
                    student.getNotes()));
            askToAddNotes(singletonScanner.getScanner(), student);
        } else {
            askToCreateNewStudent(students, singletonScanner.getScanner(), name);
        }

        System.out.println(students);
    }

    private static void askToAddNotes(Scanner scanner, Student student) {
        Confirmation confirm;
        TextInput input;
        confirm = new Confirmation(scanner, "Voulez-vous ajouter des notes? ");
        if (confirm.askOK()) {
            NoteListInput noteListInput = new NoteListInput(scanner, "Saisir les notes séparées par des espaces >");
            Float[] notes  = noteListInput.ask();
            for (Float f : notes) {
                student.getNotes().add(f);
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
