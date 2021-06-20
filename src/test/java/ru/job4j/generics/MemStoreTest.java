package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MemStoreTest {
    MemStore<User> memStore = new MemStore<>();
    User user1 = new User("1", "test1");
    User user2 = new User("2", "test2");
    User user3 = new User("3", "test3");
    User user4 = new User("4", "test4");

    @Before
    public void init() {
        memStore.add(user1);
        memStore.add(user2);
        memStore.add(user3);
        memStore.add(user4);
    }

    @Test
    public void whenAddModel() {
        assertEquals(user1, memStore.findById("1"));
        assertEquals(user2, memStore.findById("2"));
        assertEquals(user3, memStore.findById("3"));
        assertEquals(user4, memStore.findById("4"));
    }

    @Test
    public void whenReplace() {
        memStore.replace("3", user4);
        assertEquals(user4, memStore.findById("4"));
        assertEquals(user4, memStore.findById("4"));
    }

    @Test
    public void whenDelete() {
        assertTrue(memStore.delete("4"));
    }

    @Test
    public void whenFindById() {
        assertEquals(user2, memStore.findById("2"));
    }

    @Test
    public void whenFindNull() {
        assertNull(memStore.findById("5"));
    }

}