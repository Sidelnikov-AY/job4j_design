package ru.job4j.generics;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    private SimpleArray<Integer> simpleArray;

    @Test
    public void whenAddModel() {
        simpleArray = new SimpleArray<>(3);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);

        assertThat(simpleArray.get(0), is(1));
        assertThat(simpleArray.get(1), is(2));
        assertThat(simpleArray.get(2), is(3));
    }

    @Test
    public void whenRemoveSecondModel() {
        simpleArray = new SimpleArray<>(3);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.remove(1);
        assertThat(simpleArray.get(0), is(1));
        assertThat(simpleArray.get(1), is(3));
    }

    @Test
    public void whenRemoveLastModel() {
        simpleArray = new SimpleArray<>(3);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.remove(2);
        assertThat(simpleArray.get(0), is(1));
        assertThat(simpleArray.get(1), is(2));
    }

    @Test
    public void whenSetModel() {
        simpleArray = new SimpleArray<>(3);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        assertThat(simpleArray.get(0), is(1));
        assertThat(simpleArray.get(1), is(2));
        assertThat(simpleArray.get(2), is(3));
        simpleArray.set(2, 4);
        assertThat(simpleArray.get(2), is(4));
    }

    @Test
    public void whenIteratorOk() {
        simpleArray = new SimpleArray<>(3);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        Iterator<Integer> simpleArrayIt = simpleArray.iterator();
        assertThat(simpleArrayIt.hasNext(), is(true));
        assertThat(simpleArrayIt.next(), is(1));
        assertThat(simpleArrayIt.hasNext(), is(true));
        assertThat(simpleArrayIt.next(), is(2));
        assertThat(simpleArrayIt.hasNext(), is(true));
        assertThat(simpleArrayIt.next(), is(3));
        assertThat(simpleArrayIt.hasNext(), is(false));
    }
}