package com;

import java.util.Arrays;

public class Array {
    private float[] values; //variable membre
    private float max;

    public Array(int size) {
        values = new float[size];
    }

    public Array(float first, float... rest) {
        //remplissage du tableau "values"
        values = new float[rest.length + 1];
        values[0] = first;
        for (int i = 1; i < values.length; i++) {
            values[i] = rest[i - 1];
        }

        max = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] > max)
                max = values[i];
        }
    }

    public float get(int index) {
        return values[index];
    }

    public float max() {
        return max;
    }

    public float badMax(float first, float... values) {
        float max = first;
        for (float x : values) {
            if (x > max)
                max = x;
        }
        return max;
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < values.length; i++) {
            res += String.format("%3.1f", values[i]);
            if (i < values.length - 1)
                res += " ";
        }
        return res;
    }
}
