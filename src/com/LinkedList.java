package com;

public class LinkedList {

    private LinkedList next;
    private Object content;
    private String separator;

    LinkedList(String separator){
        this(null, separator);
    }

    LinkedList(Object first, String separator) {
        this.next = null;
        this.content = first;
        this.separator = separator;
    }

    void append(Object o) {
        LinkedList current = this;
        if (this.content == null) {
            this.content = o;
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.next = new LinkedList(o, separator);
        }
    }

    @Override
    public String toString() {
        LinkedList current = this;
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
