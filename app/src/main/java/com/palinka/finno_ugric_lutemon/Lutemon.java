package com.palinka.finno_ugric_lutemon;

public abstract class Lutemon {
    public String name;
    public String color;
    public int attack; // Stats for the lutemon
    public int defense;
    public int testosterone; // Custom stat of our own, will probably be health regeneration?
    public int experience;
    public int level;
    // health represents the CURRENT health.
    public int health;
    // maxHealth is the health that is used for healing lutemons back to the original health
    public int maxHealth;
    public int id;
    private int idCounter;
    public Lutemon(String name, String color, int attack, int defense, int testosterone, int experience, int level, int health, int maxHealth, int id) {
        // CONSTRUCTOR
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defense = defense;
        this.testosterone = testosterone;
        this.experience = experience;
        this.level = level;
        this.health = health;
        this.maxHealth = maxHealth;
        this.id = id;
    }
    /* MORE METHODS
    HERE
    LOL */
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
