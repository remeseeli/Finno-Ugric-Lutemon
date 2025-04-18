package com.palinka.finno_ugric_lutemon;
import java.io.Serializable;


/**
 * This class represents a lutemon (abstract)
 *
 */
public abstract class Lutemon implements Serializable{
    private static final long serialVersionUID = 1L; //Needed for the file handling
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
    public int attack(Enemy enemy) {
        if(Math.random() < getTestosterone() / 100.0)  {
            // Critical hit
            return Math.max(1, this.attack * 2 - enemy.defense);
        }
        else {
            // Normal hit
            return Math.max(1, this.attack - enemy.defense);
        }
    }

    public void gainXP(int experience) {
        this.experience += experience;
        while (this.experience >= getRequiredExperience()) {
            levelUp();
        }
    }
    protected abstract void levelUp(); // level up function to be implemented in each class for different stat growth for different types


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
    public int getId() {return id;}

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

    //this method is needed for the spinner in the TrainActivity
    @Override
    public String toString() {return name;}
}