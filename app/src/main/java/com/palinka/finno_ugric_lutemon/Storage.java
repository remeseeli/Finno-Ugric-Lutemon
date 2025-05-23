package com.palinka.finno_ugric_lutemon;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private static final Storage instance = new Storage(); // Final and eager initialization
    // HashMap that stores Lutemon objects with their IDs as keys. Used in most methods to access the lutemons.
    private HashMap<Integer, Lutemon> lutemonStorage = new HashMap<>();
    // ID that is used for the lutemons. It starts from 0 and is incremented for every new lutemon created.
    private int nextId = 0;
    // Private constructor to prevent instantiation from outside the class (Singleton pattern)
    private Storage(){}

    // Storage singleton
    public static Storage getInstance() {
        return instance;
    }

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
    */
    public Lutemon getLutemon(int id) {
        if (lutemonStorage.get(id) != null) { //In case of a valid id, it returns the object
            return lutemonStorage.get(id);
        } else {
            return null; //In case of an invalid id, it returns null
        }
    }
    //getter for the hashmap
    public HashMap<Integer, Lutemon> getLutemonMap() {
        return lutemonStorage;
    }
    // This method was used to test if the HashMap is working correctly. Not in use anymore.
    public void listLutemons(HashMap<Integer, Lutemon> lutemons) {
        for (Map.Entry<Integer, Lutemon> entry : lutemons.entrySet()) {
            int id = entry.getKey();
            Lutemon lutemon = entry.getValue();
            System.out.println("ID: " + id + "  Name: " + lutemon.name);
        }
    }


    // This method calculates the average level of all Lutemons in the storage.
    // It is used to scale the enemy level in the generateEnemy method.
    public int getAveragePlayerLevel(HashMap<Integer, Lutemon> lutemonStorage) {
        if (lutemonStorage.isEmpty()) return 1; // Default level if no Lutemons exist
        // Loop through hashmap and get average level of lutemons (we may need a get weakest and get strongest OR filter the extreme values out)
        int totalLevel = 0;
        for (Lutemon lutemon : lutemonStorage.values()) {
            totalLevel += lutemon.level;
        }
        return totalLevel / lutemonStorage.size(); // Average level
    }
    // This method generates an enemy based on the average level of the player's Lutemons.
    public Enemy generateEnemy(HashMap<Integer, Lutemon> lutemonStorage) {
        int baseLevel = getAveragePlayerLevel(lutemonStorage);
        /* The enemy level scaling works here by taking a Math.random() between 0.0 and 2.99.. and casting it into an int
        The Math.max makes sure the enemy level is not -1, the enemy level difference is in range [-1, 1]
        If you want to adjust the enemy level scaling to be more broad, change the Math.random multiplier
        If you want it to be more fair, adjust the subtracting number */
        int enemyLevel = Math.max(1, baseLevel + (int)(Math.random() * 2) - 1);
        // Enemy stats will scale according to their level Adjusting these stats will change the difficulty of the game
        // Attack, defense, and health are calculated based on the enemy level, and you can modify level scaling or the stat scaling here
        int attack = 2 + (enemyLevel * 2);  // Attack scales with level
        int defense = 2 + enemyLevel; // Defense scales slower
        int health = 20 + (enemyLevel * 3); // More HP per level
        int maxHealth = health; // Max health is the same as current health for enemies
        return new Enemy("Enemy Lutémon", enemyLevel, attack, defense, health, maxHealth);
    }

    //List lutemons method may not be needed to implement here, at least I don't know yet how, because it should be done with the recycle view.
    //Also some sorting (like according to color OR according to level OR experience) could be implemented later on.
}
