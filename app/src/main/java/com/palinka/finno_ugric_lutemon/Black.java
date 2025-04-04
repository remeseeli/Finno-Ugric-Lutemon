package com.palinka.finno_ugric_lutemon;

public class Black extends Lutemon {

    public Black() {
    super("defaultBlack", "Black", 9, 0, 1, 0, 1, 16, 16, 69);
    }
    @Override
    protected void levelUp() {
        this.experience -= getRequiredExperience(); // Subtract required XP to level up, so XP can be carried over for next level
        this.level++; // The actual level up
        this.attack += 5;
        this.defense += 1;
        this.maxHealth += 4;
        System.out.println(name + " leveled up to " + level);
    }
}
