package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray2;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArray2<T> set = new SimpleArray2<>();

    @Override
    public boolean add(T value) {
        Iterator<T> i = set.iterator();
        while (i.hasNext()) {
            if (i.next() == value) {
                return false;
            }
        }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        Iterator<T> i = set.iterator();
        while (i.hasNext()) {
            if (i.next() == value) {
                return true;

            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}