package com;

public abstract class LinkedList<T> {

    private LinkedList<T> next;
    private T content;
    protected String separator;

    LinkedList(String separator){
        this(null, separator);
    }

    LinkedList(T first, String separator) {
        this.next = null;
        this.content = first;
        this.separator = separator;
    }

    protected abstract LinkedList<T> create(T o);

    void append(T o) {
        LinkedList<T> current = this;
        if (this.content == null) {
            this.content = o;
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.next = current.create(o);
        }
    }

    @Override
    public String toString() {
        LinkedList<T> current = this;
        StringBuilder sb = new StringBuilder();
        if (content != null) {
            while (current != null) {
                sb.append(current.content.toString());
                sb.append(separator);
                current = current.next;
            }
        }

        return sb.toString();
    }
}
