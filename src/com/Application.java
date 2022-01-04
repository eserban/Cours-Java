package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Application {
    private final static String FILENAME = "students.txt";
    private File fileStudents;
    private final StudentMap students;
    private Scanner cli;

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    public Application() {
        cli = SingletonScanner.getInstance().getScanner();
        fileStudents = new File(Application.FILENAME);
        students = new StudentMap();

        try {
            students.load(fileStudents);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private void run() {
        String name;
        do {
            System.out.println(students);

            TextInput command = new TextInput(cli, "Nom de l'étudiant(e) [ENTER pour quitter]");
            name = command.ask().trim();

            Student student;
            if (students.containsKey(name)) {
                student = students.get(name);
                displayNotes(student);
                askToAddNotes(student);
            } else if (!name.isBlank()) {
                askToCreateNewStudent(name);
            }
        } while (!name.isBlank());

        try {
            students.save(fileStudents);
        } catch (IOException e) {
            System.err.println("Can't save file");
        }
    }

    private void displayNotes(Student student) {
        System.out.printf("Notes de %s : %s%n",
                student.getFullName(),
                student.getNotes());
    }

    private void askToAddNotes(Student student) {
        Confirmation confirm = new Confirmation(cli, "Voulez-vous ajouter des notes ?");
        if (confirm.askOK()) {
            NoteListInput noteListInput = new NoteListInput(cli, "Saisir les notes séparées par des espaces");
            Float[] notes = noteListInput.ask();
            student.getNotes().addAll(Arrays.asList(notes));
        }
    }

    private void askToCreateNewStudent(String name) {
        Confirmation confirm = new Confirmation(cli, "Cet étudiant est inconnu. Voulez-vous le créer ?");
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
