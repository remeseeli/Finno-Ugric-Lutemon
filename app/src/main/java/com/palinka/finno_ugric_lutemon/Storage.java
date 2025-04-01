package com.palinka.finno_ugric_lutemon;

import java.util.HashMap;

public class Storage {
    private HashMap<Integer, Lutemon> lutemonStorage = new HashMap<>();
    private int nextId = 0;

    //*
    //This method adds the lutemon objects to the gathering hashmap
    //The id value starts at 0 and every created object adds one to the id value
    //@param int id is the id that is assigned to the objects
    // */
    public int addLutemon(Lutemon lutemon) {
        int id = nextId++;
        lutemonStorage.put(id, lutemon);
        return id; //Return the ID number used for incrementation
    }
    //*
    //This method returns the object (specific lutemon) according to the assigned id]
    //*/
    public Lutemon getLutemon(int id) {
        if (lutemonStorage.get(id) != null) { //In case of a valid id, it returns the object
            return lutemonStorage.get(id);
        } else {
            return null; //In case of an invalid id, it returns null
        }
    }

    //List lutemons method may not be needed to implement here, at least I don't know yet how, because it should be done with the recycle view.
    //Also some sorting (like according to color OR according to level OR experience) could be implemented later on.
}
