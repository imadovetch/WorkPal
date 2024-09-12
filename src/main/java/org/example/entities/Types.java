package org.example.entities;

import java.util.List;

public class Types {
    private int id;
    private String name;
    public static List<Types> Types;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Types{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
