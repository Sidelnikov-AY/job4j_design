package ru.job4j.it;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenRemoveIfOdd() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ListUtils.removeIf(input, (n) -> n % 2 !=  0);
        assertThat(Arrays.asList(0, 2), Is.is(input));
    }

    @Test
    public void whenReplaceAllOne() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 1, 1, 2, 3));
        ListUtils.replaceIf(input, (n) -> n == 1, 0);
        assertThat(Arrays.asList(0, 0, 0, 0, 2, 3), Is.is(input));
    }

    @Test
    public void whenRemovedAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> toRemove = new ArrayList<>(Arrays.asList(1, 3, 5));
        ListUtils.removeAll(input, toRemove);
        assertThat(Arrays.asList(2, 4), Is.is(input));
    }
}