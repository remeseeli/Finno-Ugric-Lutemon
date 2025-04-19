package com.palinka.finno_ugric_lutemon;

/**
 * Orange class represents a subclass of Lutemon.
 * @see Lutemon
 */
public class Orange extends Lutemon {
    public Orange() {
        super("defaultOrange", "Orange", 8, 1, 1, 0, 1, 17, 17, 0);


    }
    @Override
    protected void levelUp() {
        this.experience -= getRequiredExperience(); // Subtract required XP to level up, so XP can be carried over for next level
        this.level++; // The actual level up
        this.attack += 4;
        this.defense += 1;
        this.maxHealth += 4;
        System.out.println(name + " leveled up to " + level);
    }
}
