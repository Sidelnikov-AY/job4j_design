package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    int size = 0;

    public int getSize() {
        return this.size;
    }

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
    }

    public boolean revert() {
        if (head == null || head.next == null) {
            return false;
        }
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            Node<T> next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        return true;
    }

    public void addFirst(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        node.next = head;
        head = node;
        size++;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        final T element = head.value;
        head = head.next;
        size--;
        return element;
    }

    public T deleteLast() {
        if (head.next == null) {
            final T element = head.value;
            head.value = null;
            return element;
        }

        Node<T> temp = head;
        Node<T> tail = null;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        tail = temp.next;
        final T element = tail.value;
        tail.value = null;
        temp.next = null;
        size--;
        return element;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}