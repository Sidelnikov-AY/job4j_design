package ru.job4j.generics;

import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int size = 0;

    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    public void add(T model) {
        array[size++] = model;
    }

    public T get(int index) {
        return (T) array[index];

    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
