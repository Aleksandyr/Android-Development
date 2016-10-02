package com.example.akamenov.a01grocery_store_items_through_database;

/**
 * Created by AKamenov on 10/2/2016.
 */

public class FoodStuff {

    private int _id;
    private String _name;

    public FoodStuff() {
        this.set_id(0);
        this.set_name("Default");
    }

    public FoodStuff(int id, String name) {
        this.set_id(id);
        this.set_name(name);
    }

    public int get_id() {
        return this._id;
    }

    public void set_id(int id) {
        this._id = id;
    }

    public String get_name() {
        return this._name;
    }

    public void set_name(String name) {
        this._name = name;
    }
}
