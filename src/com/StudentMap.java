package com;
import java.io.File;
import java.io.FileNotFoundException;
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
            //Create student
            Student student = new Student(fields[0]);
            put(student.getShortName(), student);
            //Add notes
            for (int i = 1; i < fields.length; i++) {
                student.getNotes().add(Float.parseFloat(fields[i]));
            }
        }
        scanner.close();
    }

    NoteList getNotes(String name) {
        return get(name).getNotes();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Student student : values()){
            sb.append(student);
            sb.append("\n");
        }
        return sb.toString();
    }
}

