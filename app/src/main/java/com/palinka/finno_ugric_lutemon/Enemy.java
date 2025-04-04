package com.palinka.finno_ugric_lutemon;

public class Enemy {
    String name;
    int attack;
    int defense;
    int level;
    int health;
    //Constructor
    public Enemy(String name,  int attack, int defense, int level, int health) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.level =level;
        this.health = health;
    }
    public int attack(Lutemon player) {
        return Math.max(0, this.attack - player.defense);
    }
    public String getName() {
        return this.name;
    }
}
