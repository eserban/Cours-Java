package com;

public class Student {
    private String shortName, fullName;
    private NoteList notes;

    public Student(String fullName) {
        this.fullName = fullName;
        shortName = fullName.split(" ")[1];
        notes = new NoteList();
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public NoteList getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return String.format("%s %s", fullName, notes);
    }
}
