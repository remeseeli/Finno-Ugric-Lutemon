package com.palinka.finno_ugric_lutemon;

/**
 * Chromatic class represents a subclass of Lutemon.
 * @see Lutemon
 */
public class Chromatic extends Lutemon {
    public Chromatic() {
        super("defaultChromatic", "Chromatic", 10, 10, 10, 0, 10, 10, 10, 10);
        // This is the "Chromatic" Lutemon type, that was meant to be used as the special "Janne" lutemon
        // Due to time constraints , was not implemented in the game
    }
    @Override
    protected void levelUp() {
        this.experience -= getRequiredExperience(); // Subtract required XP to level up, so XP can be carried over for next level
        this.level++; // The actual level up
        this.attack += 5;
        this.defense += 5; // For chromatic, my idea for the stats is to have the defense growth be very high, but to not grow max health too much -eeli
        this.maxHealth += 1;
        System.out.println(name + " leveled up to " + level);
    }
}
