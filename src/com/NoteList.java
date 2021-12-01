package com;

public class NoteList extends LinkedList<Float> {

    NoteList() {
        super(" ");
    }

    NoteList(Float note) {
        super(note, " ");
    }

    @Override
    protected LinkedList<Float> create(Float note) {
        return new NoteList(note);
    }
}