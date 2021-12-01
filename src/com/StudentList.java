package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StudentList extends LinkedList{
    private StudentList next;
    private Student student;

    StudentList(){
        super("\n");
    }

    StudentList(Student student) {
        super(student, "\n");
    }


//    public static StudentList from(File file) throws FileNotFoundException {
//        Scanner scanner = new Scanner(file);
//        String name= scanner.nextLine();
//        StudentList sl = new StudentList(new Student(name));
//        while (scanner.hasNextLine()) {
//            name = scanner.nextLine();
//            sl.append(new Student(name));
//        }
//
//        scanner.close();
//        return sl;
//    }

    void load(File file) throws FileNotFoundException {
        Scanner  scanner = new Scanner(file);
        String line;
        String[] fields;
        while (scanner.hasNextLine()){
            line = scanner.nextLine();
            fields = line.split("\t");
            //Create student
            Student student = new Student(fields[0]);
            append(student);
            //Add notes
            for (int i = 1; i < fields.length; i++) {
                student.getNotes().append(Float.parseFloat(fields[i]));
            }
        }
        scanner.close();
    }
}
