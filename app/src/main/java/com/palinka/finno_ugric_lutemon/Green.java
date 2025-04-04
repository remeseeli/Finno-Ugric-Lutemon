package com.palinka.finno_ugric_lutemon;

public class Green extends Lutemon {
    public Green() {
        super("defaultGreen", "Green", 6, 3, 1, 0, 1, 19, 19, 0);

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
