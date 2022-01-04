package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class StudentMap extends HashMap<String, Student> {
    void load(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String line;
        String[] fields;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            fields = line.split("\t");
            if (fields.length > 1) {
                //Create student
                Student student = new Student(fields[0]);
                put(student.getShortName(), student);
                //Add notes
                try {
                    fields = fields[1].split(" ");
                    for (int i = 0; i < fields.length; i++) {
                        student.getNotes().add(Float.valueOf(fields[i]));
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Can't read student " + student.getFullName());
                }
            }
        }
        scanner.close();
    }

    void save(File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(toString());
        fileWriter.close();
    }

    NoteList getNotes(String name) {
        return get(name).getNotes();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Student student : values()) {
            sb.append(student);
            sb.append("\n");
        }
        return sb.toString();
    }
}
