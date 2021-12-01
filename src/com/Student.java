package com;

public class Student {
    private String name;
    private NoteList notes;

    public Student(String name) {
        this.name = name;
        notes = new NoteList();
    }

    public NoteList getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, notes);
    }
}
