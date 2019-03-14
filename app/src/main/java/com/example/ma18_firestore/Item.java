package com.example.ma18_firestore;


public class Item {
    String name;
    boolean done;
    String id;

    public void setId(String id) {
        this.id = id;
    }

    // måste finnas med en default konstruktör för att
    // det ska gå att generera objekt utifrån firebase-snapshot
    public Item() {}

    public Item(String name, boolean done) {
        this.name = name;
        this.done = done;
    }



    public void switchDone() {
        done = !done;
    }

}
