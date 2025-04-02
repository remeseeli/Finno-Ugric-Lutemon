package com.palinka.finno_ugric_lutemon;

public class Enemy extends Lutemon {
    //Constructor
    public Enemy(String name, String type, int attack, int defense, int speed, int experience, int level, int health, int maxHealth, int id) {
        super(name, type, attack, defense, speed, experience, level, health, maxHealth, id);
    }

    //Scale difficulty
    public void scaledDifficulty(){

        this.attack = this.attack + 10;
    }
    @Override
    protected void levelUp() {
        this.experience -= getRequiredExperience(); // Subtract required XP to level up, so XP can be carried over for next level
        this.level++; // The actual level up
        this.attack += 4;
        this.defense += 1;
        this.maxHealth += 2;
        this.health = this.maxHealth; // Heal fully on level up
        System.out.println(name + " leveled up to " + level);
    }


}
