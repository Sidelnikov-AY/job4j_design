package ru.job4j.generics;

public class RoleStore implements Store<Role>{

    private final Store<Role> storeR = new MemStore<>();

    @Override
    public void add(Role model) {
        storeR.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return storeR.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return storeR.delete(id);
    }

    @Override
    public Role findById(String id) {
        return storeR.findById(id);
    }
}
