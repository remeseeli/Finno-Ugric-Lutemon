package com.palinka.finno_ugric_lutemon;

import java.util.HashMap;

public class Storage {
    private HashMap<Integer, Lutemon> lutemonStorage = new HashMap<>();
    private int nextId = 0;

    /**
    *This method adds the lutemon objects to the gathering hashmap
    *The id value starts at 0 and every created object adds one to the id value* */
    public int addLutemon(Lutemon lutemon) {
        int id = nextId++;
        lutemonStorage.put(id, lutemon);
        return id; //Return the ID number used for incrementation
    }
    /**
    /This method returns the object (specific lutemon) according to the assigned id]
    /*/
    public Lutemon getLutemon(int id) {
        if (lutemonStorage.get(id) != null) { //In case of a valid id, it returns the object
            return lutemonStorage.get(id);
        } else {
            return null; //In case of an invalid id, it returns null
        }
    }
    // This is to calculate difficulty -eeli
    public int getAveragePlayerLevel(HashMap<Integer, Lutemon> lutemonStorage) {
        if (lutemonStorage.isEmpty()) return 1; // Default level if no Lutemons exist
        // Loop through hashmap and get average level of lutemons (we may need a get weakest and get strongest OR filter the extreme values out
        int totalLevel = 0;
        for (Lutemon lutemon : lutemonStorage.values()) {
            totalLevel += lutemon.level;
        }
        return totalLevel / lutemonStorage.size(); // Average level
    }
    // Generating an enemy should be added here
    public Enemy generateEnemy(HashMap<Integer, Lutemon> lutemonStorage) {
        int baseLevel = getAveragePlayerLevel(lutemonStorage);
        int enemyLevel; // Implement randomness here too tired rn
        // and scaling the stats
        return new Enemy("Something", enemyLevel, attack, defense, health);
    }

    //List lutemons method may not be needed to implement here, at least I don't know yet how, because it should be done with the recycle view.
    //Also some sorting (like according to color OR according to level OR experience) could be implemented later on.
}
