package ru.job4j.generics;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RoleStoreTest {
    RoleStore roleStore = new RoleStore();
    Role role1 = new Role("1", "test1");
    Role role2 = new Role("2", "test2");
    Role role3 = new Role("3", "test3");
    Role role4 = new Role("4", "test4");


    @Before
    public void init() {
        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);
        roleStore.add(role4);
    }

    @Test
    public void whenAddModel() {
        assertEquals(role1, roleStore.findById("1"));
        assertEquals(role2, roleStore.findById("2"));
        assertEquals(role3, roleStore.findById("3"));
        assertEquals(role4, roleStore.findById("4"));
    }

    @Test
    public void whenReplace() {
        roleStore.replace("3", role4);
        assertEquals(role4, roleStore.findById("4"));
        assertEquals(role4, roleStore.findById("4"));
    }

    @Test
    public void whenDelete() {
        assertTrue(roleStore.delete("4"));
    }

    @Test
    public void whenFindById() {
        assertEquals(role2, roleStore.findById("2"));
    }

    @Test
    public void whenFindNull() {
        assertNull(roleStore.findById("5"));
    }

}
