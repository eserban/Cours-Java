package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

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
        System.out.print(">");
        String name = scanner.nextLine();
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
        System.out.print("Voulez-vous ajouter des notes (O/N)? ");
        String command = scanner.nextLine();
        if (command.equalsIgnoreCase("O")) {
            System.out.print("Saisir les notes séparées par des espaces >");
            command = scanner.nextLine();
            for (String s : command.split(" ")) {
                student.getNotes().add(Float.valueOf(s));
            }
        }
    }

    private static void askToCreateNewStudent(StudentMap students, Scanner scanner, String name) {
        System.err.print("Cet étudiant est inconnu. Voulez-vous le créer (O/N)? ");
        String command = scanner.nextLine();
        if (command.equalsIgnoreCase("O")) {
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
