package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean contains = mem.containsKey(id);
        if (contains) {
            mem.replace(id, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        boolean contains = mem.containsKey(id);
        if (contains) {
            mem.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        return mem.get(id);
    }
}
