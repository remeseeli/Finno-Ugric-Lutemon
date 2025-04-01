package com.palinka.finno_ugric_lutemon;

import java.util.HashMap;

public class Storage {
    private HashMap<Integer, Lutemon> lutemonStorage = new HashMap<>();
    private int nextId = 0;


    public int addLutemon(Lutemon lutemon) {
        int id = nextId++;
        lutemonStorage.put(id, lutemon);
        return id; //Return the ID number used for incrementation
    }
}
