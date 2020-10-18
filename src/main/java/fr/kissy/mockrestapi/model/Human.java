package fr.kissy.mockrestapi.model;

import java.util.List;

public class Human {
    private String name;
    private int age;
    private List<Human> descendants;
    private boolean counted = false;

    public int familySize() {
        if (counted) {
            return 0;
        }
        counted = true;
        if (descendants == null) {
            return 1;
        }
        return 1 + descendants.stream().mapToInt(Human::familySize).sum();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Human> getDescendants() {
        return descendants;
    }

    public void setDescendants(List<Human> descendants) {
        this.descendants = descendants;
    }
}
