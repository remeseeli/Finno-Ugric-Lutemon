package com.palinka.finno_ugric_lutemon;

public class White extends Lutemon {
    public White() {
        super("defaultWhite", "White", 5, 6, 1, 0, 1, 20, 20, 0);

    }
    @Override
    protected void levelUp() {
        this.experience -= getRequiredExperience(); // Subtract required XP to level up, so XP can be carried over for next level
        this.level++; // The actual level up
        this.attack += 3;
        this.defense += 2;
        this.maxHealth += 5;
        System.out.println(name + " leveled up to " + level);
    }
}
