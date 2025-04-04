package com.palinka.finno_ugric_lutemon;

public class Pink extends Lutemon {
    public Pink() {
        super("defaultPink", "Pink", 7, 2, 1, 0, 1, 18, 18, 0);

    }
    @Override
    protected void levelUp() {
        this.experience -= getRequiredExperience(); // Subtract required XP to level up, so XP can be carried over for next level
        this.level++; // The actual level up
        this.attack += 4;
        this.defense += 1;
        this.maxHealth += 5;
        System.out.println(name + " leveled up to " + level);
    }
}
