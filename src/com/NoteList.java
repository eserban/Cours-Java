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

    NoteList(Object o) {
        super(o, " ");
    }

    @Override
    protected LinkedList create(Object o) {
        return new NoteList(o);
    }
}