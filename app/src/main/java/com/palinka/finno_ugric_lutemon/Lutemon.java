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
    // baseExperience is the base XP points, and is used for scaling difficulty
    private final int baseExperience = 100;
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

    public void gainXP(int experience) {
        this.experience += experience;
        while (this.experience >= getRequiredExperience()) {
            levelUp();
        }
    }

    private void levelUp() {
        this.experience -= getRequiredExperience(); // Subtract required XP to level up, so XP can be carried over for next level
        this.level++; // The actual level up
        this.attack += 2;
        this.defense += 1;
        this.maxHealth += 5;
        this.health = this.maxHealth; // Heal fully on level up
        System.out.println(name + " leveled up to " + level);
    }

    //Getter methods for each variable
    public int getRequiredExperience() {
        return baseExperience * level;
    }
    public String getName() {return name;}
    public String getColor() {return color;}
    public int getAttack() {return attack;}
    public int getDefense() {return defense;}
    public int getTestosterone() {return testosterone;}
    public int getExperience() {return experience;}
    public int getLevel() {return level;}
    public int getHealth() {return health;}
    public int getMaxHealth() {return maxHealth;}
    public int getId() { return id; }
    //Setter methods for each variable
    public void setName(String name) {this.name = name;}
    public void setColor(String color) {this.color = color;} //May be deleted if unused
    public void setAttack(int attack) {this.attack = attack;}
    public void setDefense(int defense) {this.defense = defense;}
    public void setTestosterone(int testosterone) {this.testosterone = testosterone;}
    public void setExperience(int experience) {this.experience = experience;}
    public void setLevel(int level) {this.level = level;}
    public void setHealth(int health) {this.health = health;}
    public void setMaxHealth(int maxHealth) {this.maxHealth = maxHealth;}
}