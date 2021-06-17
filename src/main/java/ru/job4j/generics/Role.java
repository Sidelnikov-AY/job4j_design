package ru.job4j.generics;

public class Role extends Base {
    private String anyField;
    public Role(String id, String anyField){
        super(id);
        this.anyField = anyField;
    }
}
