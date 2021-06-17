package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray2Test {

    @Test
    public void whenAddThenGet() {
        SimpleArray2<String> array = new SimpleArray2<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleArray2<String> array = new SimpleArray2<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleArray2<String> array = new SimpleArray2<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleArray2<String> array = new SimpleArray2<>();
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleArray2<String> array = new SimpleArray2<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleArray2<String> array = new SimpleArray2<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }

    @Test
    public void whenAdd2ElementThenGet4Elemnet() {
        SimpleArray2<String> array = new SimpleArray2<>(2);
        array.add("first");
        array.add("second");
        array.add("third");
        array.add("four");
        String rsl = array.get(2);
        String rsl2 = array.get(3);
        assertThat(rsl, is("third"));
        assertThat(rsl2, is("four"));
    }
}