package com.palinka.finno_ugric_lutemon;

public abstract class Lutemon {
    public String name;
    public String color;
    public int attack; // Stats for the lutemon
    public int defense;
    public int testosterone; // Custom stat of our own, will probably be health regeneration?
    public int level;
    // health represents the CURRENT health.
    public int health;
    // maxHealth is the health that is used for healing lutemons back to the original health
    public int maxHealth;
    public int id;
    private int idCounter;
    public Lutemon() {
        // CONSTRUCTOR
    }
}
