package com.palinka.finno_ugric_lutemon;

public class Chromatic extends Lutemon {
    public Chromatic() {
        super("defaultChromatic", "Chromatic", 10, 10, 10, 10, 10, 10, 10, 10);

    }
    @Override
    protected void levelUp() {
        this.experience -= getRequiredExperience(); // Subtract required XP to level up, so XP can be carried over for next level
        this.level++; // The actual level up
        this.attack += 5;
        this.defense += 5; // For chromatic, my idea for the stats is to have the defense growth be very high, but to not grow max health too much -eeli
        this.maxHealth += 1;
        this.health = this.maxHealth; // Heal fully on level up
        System.out.println(name + " leveled up to " + level);
    }
}
