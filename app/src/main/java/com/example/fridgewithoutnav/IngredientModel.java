package com.example.fridgewithoutnav;

import java.util.Date;

public class IngredientModel {
    private int id;
    private String name;
    private int date;

    //constructors

    public IngredientModel(int id, String name, int date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    //toString is necessary for printing the contents of class

    @Override
    public String toString() {
        return "IngredientModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }

    // getter and setter

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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
