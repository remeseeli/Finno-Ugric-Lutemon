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


}
