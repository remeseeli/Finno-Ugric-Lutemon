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

}
