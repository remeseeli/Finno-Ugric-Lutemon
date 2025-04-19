package com.palinka.finno_ugric_lutemon;
// enemy class that serves as the opponent for player in battle.
public class Enemy {
    // Enemy attributes; only necessary attributes for battle are included
    // name is redundant in current implementation, but it can be used for future features
    String name;
    int attack;
    int defense;
    int level;
    public int health;
    int maxHealth;
    //Constructor
    public Enemy(String name,  int attack, int defense, int level, int health, int maxHealth) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.level =level;
        this.health = health;
        this.maxHealth = maxHealth;
    }
    // Attack method. Calculates damage based on enemy's attack and player's defense. Mirrors Lutemon's attack method.
    // Critical hit chance is 5% (0.05) and doubles the damage.
    public int attack(Lutemon player) {
        if(Math.random()<0.05)  {
            // Critical hit
            return Math.max(1, this.attack * 2 - player.defense);
        }
        else {
            // Normal hit
            return Math.max(1, this.attack - player.defense);
        }
    }
    // Getters for enemy attributes
    public String getName() { return this.name; }
    public int getLevel() { return this.level; }
    public int getHealth() { return this.health; }
    public int getMaxHealth() { return this.maxHealth;}
    public int setHealth(int damage) { return this.health -= damage;  }
}
