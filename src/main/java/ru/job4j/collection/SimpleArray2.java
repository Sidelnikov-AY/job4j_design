package ru.job4j.collection;

import java.util.*;

public class SimpleArray2<T> implements Iterable<T> {
    private Object[] container;

    private int size = 0;
    int initCapacity;
    int modCount = 0;

    public SimpleArray2() {
        this.container = new Object[1];
    };

    public SimpleArray2(int initCapacity) {
        container = new Object[initCapacity];
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        if (size >= container.length) {
            container = Arrays.copyOf(container, container.length + 2);
        }
        modCount++;
        container[size++] = model;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[point++];
            }
        };
    }
}