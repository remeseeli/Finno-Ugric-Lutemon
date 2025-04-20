package com.palinka.finno_ugric_lutemon;
import java.io.Serializable;


/**
 * This class represents a Lutemon, which is a creature that can battle and train.
 * It has stats, such as attack, defense, and testosterone, which can be leveled up.
 * It also has a name, and most importantly color, since it has several subclasses that represent different types of Lutemons.
 */
public abstract class Lutemon implements Serializable {
    public String name;
    public String color;
    public int attack; // Stats for the lutemon
    public int defense;
    public int testosterone; // Custom stat of our own, that is used for critical hits
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
    private long lastTime = 0; // This is used for the training timer, to check if the lutemon has been trained recently

    //Attributes for the statictics
    private int numberOfwins = 0;
    private int numberOflosses = 0;
    private int numberOfBattles = 0;
    private int numberOfTrainings = 0;

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
        this.lastTime = 0;
    }
    // Lutemon's attack method, which calculates damage based on attack and enemy's defense.
    // Critical hit chance is testosterone in percents (1 = 1% chance).
    public int attack(Enemy enemy) {
        int baseDamage = this.attack - enemy.defense;
        int randomFactor = (int)(Math.random() * 5);  //This random factor will give us random extra damage between 0 and 4 (hopefully)
        int damage = baseDamage + randomFactor;
        //Critical hit
        if(Math.random() < getTestosterone() / 100.0)  {
            damage *= 2;

        }
        // Normal hit
        return Math.max(1, damage);
    }


    // Level up method, that allows for overflow of experience points for the next level.
    public void gainXP(int experience) {
        this.experience += experience;
        while (this.experience >= getRequiredExperience()) {
            levelUp();
        }
    }
    protected abstract void levelUp(); // level up function to be implemented in each class for different stat growth for different types


    //Getter methods for each variable
    public int getRequiredExperience() { return baseExperience * level; }
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

    public int getNumberOfWins() {return numberOfwins;}
    public int getNumberOfLosses() {return numberOflosses;}
    public int getNumberOfBattles() {return numberOfBattles;}
    public int getNumberOfTrainings() {return numberOfTrainings;}
    public long getLastTrainingTime() {return lastTime;} // Getter for the training timer

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
    public void setLastTime (long lastTime) {this.lastTime = lastTime;} // Setter for the training timer

    //Setter methods for the statistics
    public void incrementWins() {this.numberOfwins++;}
    public void incrementLosses() {this.numberOflosses++;}
    public void incrementBattles() {this.numberOfBattles++;}
    public void incrementTrainings() {this.numberOfTrainings++;}


    //this method is needed for the spinner in the TrainActivity
    @Override
    public String toString() {return name;}
}
