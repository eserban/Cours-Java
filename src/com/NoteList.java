package com;

public class NoteList extends LinkedList {
    private NoteList next;
    private Float note;

    NoteList() {
        super(" ");
    }

    NoteList(Float note) {
        super(note, " ");
    }
}