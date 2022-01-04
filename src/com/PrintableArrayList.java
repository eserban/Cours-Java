package com;

import java.util.ArrayList;

public class PrintableArrayList<T> extends ArrayList<T> {
    private String separator;

    PrintableArrayList(String separator) {
        this.separator = separator;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //boucle indexée
        for (int i = 0; i < size(); i++) {
            sb.append(get(i));
            if (i < size() - 1) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }
}
