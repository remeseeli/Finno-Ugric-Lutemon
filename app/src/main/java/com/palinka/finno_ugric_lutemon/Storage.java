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
    public Enemy generateEnemy(HashMap<Integer, Lutemon> lutemonStorage) {
        int baseLevel = getAveragePlayerLevel(lutemonStorage);
        /* The enemy level scaling works here by taking a Math.random() between 0.0 and 2.99.. and casting it into an int
        The Math.max makes sure the enemy level is not -1, the enemy level difference is in range [-1, 1]
        If you want to adjust the enemy level scaling to be more broad, change the Math.random multiplier
        If you want it to be more fair, adjust the subtracting number */
        int enemyLevel = Math.max(1, baseLevel + (int)(Math.random() * 3) - 1);
        // Enemy stats will scale according to their level
        int attack = 5 + (enemyLevel * 2);  // Attack scales with level
        int defense = 3 + enemyLevel; // Defense scales slower
        int health = 20 + (enemyLevel * 5); // More HP per level
        return new Enemy("Something", enemyLevel, attack, defense, health);
    }

    //List lutemons method may not be needed to implement here, at least I don't know yet how, because it should be done with the recycle view.
    //Also some sorting (like according to color OR according to level OR experience) could be implemented later on.
}
