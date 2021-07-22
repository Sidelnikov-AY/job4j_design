package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleMapTest {
    private SimpleMap<Integer, String> map;

    @Test
    public void whenAddAndGetCorrect() {
        map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(3, "four");
        assertThat(map.get(1), is("one"));
        assertThat(map.get(3), is("three"));
        assertThat(map.size(), is(3));
    }

    @Test
    public void whenGetNotCorrect() {
        map = new SimpleMap<>();
        map.put(1, "one");
        assertNull(map.get(2));
    }

    @Test
    public void whenRemoveCorrect() {
        map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.remove(3);
        assertNull(map.get(3));
        assertThat(map.size(), is(2));
    }

    @Test
    public void whenRemoveNotCorrect() {
        map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        assertFalse(map.remove(3));
    }

    @Test
    public void whenExpandCorrect() {
        map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "two");
        assertThat(map.length(), is(8));
        map.put(4, "two");
        map.put(5, "two");
        map.put(6, "two");
        map.put(7, "two");
        assertThat(map.length(), is(16));
    }


    @Test
    public void whenIteratorCorrect() {
        map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "two");
        Iterator<Integer> it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIteratorThrowsConcurrentException() {
        map = new SimpleMap<>();
        map.put(1, "one");
        Iterator<Integer> it = map.iterator();
         map.put(2, "two");
        it.next();
    }
}