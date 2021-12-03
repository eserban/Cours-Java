package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StudentList extends PrintableArrayList<Student> {
    StudentList() {
        super("\n");
    }

    void load(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String line;
        String[] fields;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            fields = line.split("\t");
            //Create student
            Student student = new Student(fields[0]);
            add(student);
            //Add notes
            for (int i = 1; i < fields.length; i++) {
                student.getNotes().add(Float.parseFloat(fields[i]));
            }
        }
        scanner.close();
    }
}

